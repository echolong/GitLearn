package org.echolong.facelib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;

import org.echolong.facelib.emoji.EmojiText;
import org.echolong.facelib.listeners.OnEmojiClickedListener;


final class EmojiGridView extends FrameLayout {
    EmojiGridView(final Context context) {
        super(context);

        View.inflate(context, R.layout.emoji_grid, this);
    }

    public EmojiGridView init(final EmojiText[] emojis, @Nullable final OnEmojiClickedListener onEmojiClickedListener) {
        final GridView gridView = (GridView) findViewById(R.id.emoji_grid_view);

        final EmojiArrayAdapter emojiArrayAdapter = new EmojiArrayAdapter(getContext(), emojis);
        emojiArrayAdapter.setOnEmojiClickedListener(onEmojiClickedListener);
        gridView.setAdapter(emojiArrayAdapter);
        return this;
    }
}
