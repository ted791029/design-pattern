package com.ted.app.reviewwords.scenes;

import java.util.Set;

import com.ted.app.core.AppContext;
import com.ted.app.core.AppContextKey;
import com.ted.app.core.DefaultMenuOptionKey;
import com.ted.app.core.LifecycleType;
import com.ted.app.core.MenuBuilder;
import com.ted.app.core.Message;
import com.ted.app.core.Messenger;
import com.ted.app.core.Prompt;
import com.ted.app.core.Scene;
import com.ted.app.core.SceneId;
import com.ted.app.reviewwords.commands.SkipReviewOption;
import com.ted.app.reviewwords.commands.StartReviewOption;

public class ReviewWordsScene extends Scene {

    public ReviewWordsScene(AppContext context) {
        super(SceneId.REVIEW, "Review words", LifecycleType.CLEAR_ON_LEAVE, 0, context);
    }

    @Override
    protected Messenger renderMessenger() {
        AppContext context = getContext();
        Messenger messenger = new Messenger();
        if (context.getWordRepository().size() == 0) {
            messenger.add(new Message("You don't have any words."));
        } else {
            messenger.add(new Message("Are you ready for the review test?"));
            if (context.getTempData(AppContextKey.LATEST_POINT) != null && context.getTempData(AppContextKey.LATEST_NUM_OF_QUESTIONS) != null) {
                messenger.add(
                        new Message("Latest point: "
                                + (int) context.getTempData(AppContextKey.LATEST_POINT)
                                + "/"
                                + (int) context.getTempData(AppContextKey.LATEST_NUM_OF_QUESTIONS)));
            } else {
                messenger.add(new Message("Latest point: 0/0"));
            }
        }
        return messenger;
    }

    @Override
    protected Prompt renderPrompt() {
        return new Prompt("Answer: ");
    }

    @Override
    protected void contributeMenuOptions(MenuBuilder builder) {
        builder.add(new StartReviewOption());
        builder.add(new SkipReviewOption());
    }

    @Override
    protected Set<DefaultMenuOptionKey> hiddenDefaultMenuOptions() {
        return Set.of(DefaultMenuOptionKey.PREVIOUS_PAGE);
    }

}
