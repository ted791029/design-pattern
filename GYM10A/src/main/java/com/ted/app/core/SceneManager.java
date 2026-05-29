package com.ted.app.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SceneManager {

    private Map<SceneId, Scene> activeScenes = new HashMap<>();

    private AppContext context;

    private boolean isRunning = true;

    private Map<SceneId, SceneFactory> registeredSceneFactories = new HashMap<>();

    private Map<SceneId, List<SceneId>> sceneGraph = new HashMap<>();

    private Map<DefaultMenuOptionKey, DefaultMenuOptionFactory> registeredDefaultMenuOptionFactories = new EnumMap<>(DefaultMenuOptionKey.class);

    private Deque<SceneId> sceneStack = new ArrayDeque<>();

    public SceneManager() {
    }

    public String buildBreadcrumbPath(Scene current) {
        List<SceneId> stack = new ArrayList<>(sceneStack);
        StringBuilder path = new StringBuilder();
        for (int i = stack.size() - 1; i >= 0; i--) {
            Scene scene = reslove(stack.get(i));
            appendBreadcrumbLabel(path, scene.getBreadcrumbLabel());
        }
        return formatBreadcrumbPath(path, current);
    }

    public Scene current() {
        if (sceneStack.isEmpty()) {
            return null;
        }
        return reslove(sceneStack.peek());
    }

    public void dispose() {
        int currentDepth = sceneStack.size();
        SceneId currentSceneId = sceneStack.peek();
        Set<SceneId> toRemove = new LinkedHashSet<>();
        List<Scene> allScenes = new ArrayList<>(activeScenes.values());
        for (Scene scene : allScenes) {
            if (shouldClearOnLeave(scene, currentSceneId)) {
                scene.clearState();
                if (!sceneStack.contains(scene.getId())) {
                    toRemove.add(scene.getId());
                }
                continue;
            }

            if (shouldRemoveActiveScene(scene, currentDepth)) {
                scene.clearState();
                toRemove.add(scene.getId());
            }
        }

        toRemove.forEach(activeScenes::remove);
    }

    public void goBack() {
        if (sceneStack.size() <= 1) {
            return;
        }
        sceneStack.pop();
        dispose();
    }

    public void link(SceneId parentSceneId, SceneId childSceneId) {
        List<SceneId> children = sceneGraph.computeIfAbsent(parentSceneId, key -> new ArrayList<>());
        if (!children.contains(childSceneId)) {
            children.add(childSceneId);
        }
    }

    public void push(SceneId sceneId) {
        Scene next = reslove(sceneId);
        next.setDepth(sceneStack.size() + 1);
        sceneStack.push(sceneId);
        dispose();
    }

    public void register(SceneId sceneId, SceneFactory factory) {
        registeredSceneFactories.put(sceneId, factory);
    }

    public void registerDefaultMenuOption(DefaultMenuOptionKey option, DefaultMenuOptionFactory factory) {
        registeredDefaultMenuOptionFactories.put(option, factory);
    }

    public Scene reslove(SceneId sceneId) {
        SceneFactory factory = registeredSceneFactories.get(sceneId);
        if (factory == null) {
            throw new IllegalArgumentException("No registered scene: " + sceneId);
        }

        Scene next = activeScenes.get(sceneId);
        if (next == null) {
            next = factory.create(context);
            activeScenes.put(sceneId, next);
        }
        return next;
    }


    public List<Scene> resolveGraphChildren(SceneId parentSceneId) {
        List<SceneId> childIds = sceneGraph.getOrDefault(parentSceneId, new ArrayList<>());
        List<Scene> children = new ArrayList<>();
        for (SceneId childId : childIds) {
            children.add(reslove(childId));
        }
        return children;
    }

    public Map<DefaultMenuOptionKey, MenuOption> resolveDefaultMenuOptions() {
        Map<DefaultMenuOptionKey, MenuOption> resolved = new EnumMap<>(DefaultMenuOptionKey.class);
        for (Map.Entry<DefaultMenuOptionKey, DefaultMenuOptionFactory> entry : registeredDefaultMenuOptionFactories.entrySet()) {
            resolved.put(entry.getKey(), entry.getValue().create(context));
        }
        return resolved;
    }

    private boolean shouldRemoveActiveScene(Scene scene, int currentDepth) {
        if (scene.getLifecycleType() == LifecycleType.DISTANCE_BASED) {
            return scene.getDepth() - currentDepth >= scene.getDistance();
        }
        return false;
    }

    private boolean shouldClearOnLeave(Scene scene, SceneId currentSceneId) {
        return scene.getLifecycleType() == LifecycleType.CLEAR_ON_LEAVE
                && !scene.getId().equals(currentSceneId);
    }

    private void appendBreadcrumbLabel(StringBuilder path, String label) {
        if ("/".equals(label)) {
            appendRoot(path);
            return;
        }
        appendBreadcrumbSeparator(path);
        path.append(label);
    }

    private void appendRoot(StringBuilder path) {
        if (path.length() == 0) {
            path.append("/");
        }
    }

    private void appendBreadcrumbSeparator(StringBuilder path) {
        if (path.length() > 1) {
            path.append(" / ");
            return;
        }
        path.append(" ");
    }

    private String formatBreadcrumbPath(StringBuilder path, Scene current) {
        if (path.length() == 0 && current != null) {
            return current.getBreadcrumbLabel();
        }
        return path.toString();
    }

    // Getters and setters
    public Map<SceneId, Scene> getActiveScenes() {
        return activeScenes;
    }

    public AppContext getContext() {
        return context;
    }
    
    public boolean getIsRunning() {
        return isRunning;
    }

    public Map<SceneId, SceneFactory> getRegisteredSceneFactories() {
        return registeredSceneFactories;
    }

    public Map<SceneId, List<SceneId>> getSceneGraph() {
        return sceneGraph;
    }

    public Deque<SceneId> getSceneStack() {
        return sceneStack;
    }

    public Map<DefaultMenuOptionKey, DefaultMenuOptionFactory> getRegisteredDefaultMenuOptionFactories() {
        return registeredDefaultMenuOptionFactories;
    }

    public void setActiveScenes(Map<SceneId, Scene> activeScenes) {
        this.activeScenes = activeScenes;
    }

    public void setContext(AppContext context) {
        this.context = context;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void setRegisteredSceneFactories(Map<SceneId, SceneFactory> registeredSceneFactories) {
        this.registeredSceneFactories = registeredSceneFactories;
    }

    public void setRegisteredDefaultMenuOptionFactories(Map<DefaultMenuOptionKey, DefaultMenuOptionFactory> factories) {
        this.registeredDefaultMenuOptionFactories = factories;
    }

    public void setSceneGraph(Map<SceneId, List<SceneId>> sceneGraph) {
        this.sceneGraph = sceneGraph;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setSceneStack(Deque<SceneId> sceneStack) {
        this.sceneStack = sceneStack;
    }
}
