package org.echolong.facelib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.echolong.facelib.emoji.EmojiText;
import org.echolong.facelib.listeners.OnEmojiClickedListener;


final class EmojiArrayAdapter extends ArrayAdapter<EmojiText> {
    @Nullable
    private OnEmojiClickedListener onEmojiClickedListener;

    @SuppressWarnings("PMD.UseVarargs")
    EmojiArrayAdapter(final Context context, final EmojiText[] data) {
        super(context, R.layout.emoji_text_view, data);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.emoji_text_view, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.icon = (TextView) view.findViewById(R.id.emoji_icon);
            view.setTag(holder);
        }

        final EmojiText emoji = getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.emoji = emoji;
        EmojiTextHandler.addEmojis(getContext(), new SpannableString(emoji.getEmoji()),(int) holder.icon.getTextSize());
//        holder.icon.setText();
        holder.icon.setText(emoji.getEmoji());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onEmojiClickedListener != null) {
                    final ViewHolder tag = (ViewHolder) v.getTag();
                    onEmojiClickedListener.onEmojiClicked(tag.emoji);
                }
            }
        });

        return view;
    }

    public void setOnEmojiClickedListener(@Nullable final OnEmojiClickedListener onEmojiClickedListener) {
        this.onEmojiClickedListener = onEmojiClickedListener;
    }

    static class ViewHolder {
        EmojiText emoji;
        TextView icon;
    }
}
