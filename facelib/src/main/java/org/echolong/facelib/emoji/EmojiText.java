package org.echolong.facelib.emoji;

/**
 * Created by Administrator on 2016/3/31.
 */
public class EmojiText {
    private String emojiText;

    public EmojiText(String string) {
        emojiText = string;
    }

    public static EmojiText fromString(String text) {
        return new EmojiText(text);
    }

    public String getEmoji() {
        return emojiText;
    }
}
