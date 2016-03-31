package org.echolong.facelib;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.echolong.facelib.emoji.Teaworm;
import org.echolong.facelib.listeners.OnEmojiBackspaceClickListener;
import org.echolong.facelib.listeners.OnEmojiClickedListener;
import org.echolong.facelib.listeners.RepeatListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressLint("ViewConstructor")
final class EmojiView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final int PEOPLE_INDEX = 0;
    private static final int TEAWORM_INDEX = 1;

    private static final long INITIAL_INTERVAL = TimeUnit.SECONDS.toMillis(1) / 2;
    private static final int NORMAL_INTERVAL = 50;

    @ColorInt
    private final int themeAccentColor;

    @Nullable
    private OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    private int emojiTabLastSelectedIndex = -1;
    private final ImageView[] emojiTabs;

    EmojiView(final Context context, final OnEmojiClickedListener onEmojiClickedListener) {
        super(context);

        View.inflate(context, R.layout.emoji_view, this);

        final ViewPager emojisPager = (ViewPager) findViewById(R.id.emojis_pager);
        emojisPager.addOnPageChangeListener(this);

        final List<EmojiGridView> views = getViews(context, onEmojiClickedListener);
        final EmojiPagerAdapter emojisAdapter = new EmojiPagerAdapter(views);
        emojisPager.setAdapter(emojisAdapter);

        emojiTabs = new ImageView[TEAWORM_INDEX + 1];
        emojiTabs[PEOPLE_INDEX] = (ImageView) findViewById(R.id.emojis_tab_0_people);
        emojiTabs[TEAWORM_INDEX] = (ImageView) findViewById(R.id.emojis_tab_1_nature);

        handleOnClicks(emojisPager);

        findViewById(R.id.emojis_backspace).setOnTouchListener(new RepeatListener(INITIAL_INTERVAL, NORMAL_INTERVAL, new OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onEmojiBackspaceClickListener != null) {
                    onEmojiBackspaceClickListener.onEmojiBackspaceClicked(view);
                }
            }
        }));

        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        themeAccentColor = value.data;

        emojisPager.setCurrentItem(PEOPLE_INDEX);
        onPageSelected(PEOPLE_INDEX);
    }

    @SuppressFBWarnings(value = "SIC_INNER_SHOULD_BE_STATIC_ANON", justification = "Do not care in this one")
    private void handleOnClicks(final ViewPager emojisPager) {
        for (int i = 0; i < emojiTabs.length; i++) {
            final int position = i;
            emojiTabs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    emojisPager.setCurrentItem(position);
                }
            });
        }
    }

    public void setOnEmojiBackspaceClickListener(@Nullable final OnEmojiBackspaceClickListener onEmojiBackspaceClickListener) {
        this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
    }

    @NonNull
    private List<EmojiGridView> getViews(final Context context, @Nullable final OnEmojiClickedListener onEmojiClickedListener) {
        final EmojiGridView teawormGridView = new EmojiGridView(context).init(Teaworm.DATA, onEmojiClickedListener);
        return Arrays.asList( teawormGridView);
    }

    @Override
    public void onPageSelected(final int i) {
        if (emojiTabLastSelectedIndex != i) {
            switch (i) {
                case PEOPLE_INDEX:
                case TEAWORM_INDEX:
                    if (emojiTabLastSelectedIndex >= 0 && emojiTabLastSelectedIndex < emojiTabs.length) {
                        emojiTabs[emojiTabLastSelectedIndex].setSelected(false);
                        emojiTabs[emojiTabLastSelectedIndex].clearColorFilter();
                    }

                    emojiTabs[i].setSelected(true);
                    emojiTabs[i].setColorFilter(themeAccentColor, PorterDuff.Mode.SRC_IN);

                    emojiTabLastSelectedIndex = i;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onPageScrolled(final int i, final float v, final int i2) {
        // Don't care
    }

    @Override
    public void onPageScrollStateChanged(final int i) {
        // Don't care
    }
}
