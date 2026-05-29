package com.ted.app.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Scene implements MenuOption {

    private AppContext context;

    private Breadcrumbs breadcrumbs;

    private int depth;

    private int distance;

    private SceneId id;

    private String label;

    private LifecycleType lifecycleType;

    private Menu menu;

    private Messenger messenger;

    private String optionCustomKey;

    private String optionLabel;

    private Prompt prompt;

    private boolean visible;

    public Scene(SceneId id, String label, LifecycleType lifecycleType, int distance,  AppContext context) {
        this(id, label, lifecycleType, distance, context, true);
    }

    public Scene(SceneId id, String label, LifecycleType lifecycleType, int distance,  AppContext context, boolean visible) {
        this.context = context;
        this.id = id;
        this.label = label;
        this.optionCustomKey = null;
        this.optionLabel = label;
        this.lifecycleType = lifecycleType;
        this.distance = distance;
        this.visible = visible;
    }

    @Override
    public void action(AppContext context) {
        SceneManager manager = context.getManager();
        manager.push(id);
    }

    public void clearState() {

    }

    public void display() {
        System.out.println(breadcrumbs.displayText());
        System.out.println("---");

        if (!messenger.isEmpty()) {
            System.out.println(messenger.displayText());
            System.out.println("---");
        }

        if (!menu.isEmpty()) {
            System.out.println(menu.displayLabel());
            System.out.println("---");
        }

        System.out.println(prompt.getText());
    }

    public void handleInput(String input){

        if (input.isEmpty()) {
            return;
        }

        boolean handled = handleOptionInput(input);

        if (handled) {
            return;
        }

        handleTextInput(input);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    
    public void render(){
        renderAction();
        setBreadcrumbs(renderBreadcrumbs());
        setMessenger(renderMessenger());
        setMenu(renderMenu());
        setPrompt(renderPrompt());
        display();
    }

    protected void contributeMenuOptions(MenuBuilder builder) {

    }

    protected void handleTextInput(String input){

    }

    protected void renderAction(){

    }

    protected abstract Messenger renderMessenger();

    protected Menu renderMenu() {
        AppContext context = getContext();
        SceneManager manager = context.getManager();
        List<Scene> children = manager.resolveGraphChildren(getId());
        Map<DefaultMenuOptionKey, MenuOption> defaultMenuOptions = manager.resolveDefaultMenuOptions();
        Set<DefaultMenuOptionKey> hiddenDefaultMenuOptions = hiddenDefaultMenuOptions();
        MenuBuilder builder = new MenuBuilder();
        for (Scene child : children) {
            builder.add(child);
        }
        contributeMenuOptions(builder);
        for (Map.Entry<DefaultMenuOptionKey, MenuOption> entry : defaultMenuOptions.entrySet()) {
            if (hiddenDefaultMenuOptions.contains(entry.getKey())) {
                entry.getValue().setVisible(false);
            }
            builder.add(entry.getValue());
        }
        return builder
                .build();
    }

    protected abstract Prompt renderPrompt();

    protected Set<DefaultMenuOptionKey> hiddenDefaultMenuOptions() {
        return Set.of();
    }

    private boolean handleOptionInput(String input){
        AppContext context = getContext();
        Menu menu = getMenu();
        MenuOption option = menu.resolveOption(input);
        if (option != null) {
            option.action(context);
            return true;
        }

        return false;
    }

    private Breadcrumbs renderBreadcrumbs(){
        AppContext context = getContext();
        SceneManager manager = context.getManager();
        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.setCurrentPath(manager.buildBreadcrumbPath(this));
        return breadcrumbs;
    }


    // Getters and setters
    public AppContext getContext() {
        return context;
    }

    public String getBreadcrumbLabel() {
        return label;
    }

    public Breadcrumbs getBreadcrumbs() {
        return breadcrumbs;
    }

    public int getDepth() {
        return depth;
    }

    public int getDistance() {
        return distance;
    }

    public SceneId getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public LifecycleType getLifecycleType() {
        return lifecycleType;
    }

    public Menu getMenu() {
        return menu;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    @Override
    public String getOptionCustomKey() {
        return optionCustomKey;
    }

    @Override
    public String getOptionLabel() {
        return optionLabel;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setContext(AppContext context) {
        this.context = context;
    }

    public void setBreadcrumbs(Breadcrumbs breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setId(SceneId id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLifecycleType(LifecycleType lifecycleType) {
        this.lifecycleType = lifecycleType;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public void setOptionCustomKey(String optionCustomKey) {
        this.optionCustomKey = optionCustomKey;
    }

    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
