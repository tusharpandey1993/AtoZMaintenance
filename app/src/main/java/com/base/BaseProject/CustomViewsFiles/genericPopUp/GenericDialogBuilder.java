package com.base.BaseProject.CustomViewsFiles.genericPopUp;

import android.graphics.drawable.Drawable;
import android.text.Spanned;

public class GenericDialogBuilder {


    private String heading;
    private boolean isShowCloseButton;
    private Drawable drawableIcon;
    private String description;
    private String howItWorkHeader;
    private String howItWorkDescription;
    private String howItWorkDescription2;
    private String howItWorkDescription3;
    private Spanned spannedText;
    private String note;
    private String note2;
    private String negativeButtonText;
    private String positiveButtonText;
    private GenericDialogClickListener genericDialogClickListener;
    private int FucntionNumber;


    public GenericDialogBuilder(String heading, boolean isShowCloseButton, Drawable drawableIcon, String description, String howItWorkHeader, String howItWorkDescription, String howItWorkDescription2,String howItWorkDescription3, Spanned spannedText, String note, String note2, String negativeButtonText, String positiveButtonText, GenericDialogClickListener genericDialogClickListener, int FucntionNumber) {
        this.heading = heading;
        this.isShowCloseButton = isShowCloseButton;
        this.drawableIcon = drawableIcon;
        this.description = description;
        this.howItWorkHeader = howItWorkHeader;
        this.howItWorkDescription = howItWorkDescription;
        this.howItWorkDescription2 = howItWorkDescription2;
        this.howItWorkDescription3 = howItWorkDescription3;
        this.spannedText = spannedText;
        this.note = note;
        this.note2 = note2;
        this.negativeButtonText = negativeButtonText;
        this.positiveButtonText = positiveButtonText;
        this.genericDialogClickListener = genericDialogClickListener;
        this.FucntionNumber = FucntionNumber;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public boolean isShowCloseButton() {
        return isShowCloseButton;
    }

    public void setShowCloseButton(boolean showCloseButton) {
        isShowCloseButton = showCloseButton;
    }

    public Drawable getDrawableIcon() {
        return drawableIcon;
    }

    public void setDrawableIcon(Drawable drawableIcon) {
        this.drawableIcon = drawableIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowItWorkHeader() {
        return howItWorkHeader;
    }

    public void setHowItWorkHeader(String howItWorkHeader) {
        this.howItWorkHeader = howItWorkHeader;
    }

    public String getHowItWorkDescription() {
        return howItWorkDescription;
    }

    public void setHowItWorkDescription(String howItWorkDescription) {
        this.howItWorkDescription = howItWorkDescription;
    }

    public String getHowItWorkDescription2() {
        return howItWorkDescription2;
    }

    public void setHowItWorkDescription2(String howItWorkDescription2) {
        this.howItWorkDescription2 = howItWorkDescription2;
    }

    public String getHowItWorkDescription3() {
        return howItWorkDescription3;
    }

    public void setHowItWorkDescription3(String howItWorkDescription3) {
        this.howItWorkDescription3 = howItWorkDescription3;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public GenericDialogClickListener getGenericDialogClickListener() {
        return genericDialogClickListener;
    }

    public void setGenericDialogClickListener(GenericDialogClickListener genericDialogClickListener) {
        this.genericDialogClickListener = genericDialogClickListener;
    }

    public int getFucntionNumber() {
        return FucntionNumber;
    }

    public void setFucntionNumber(int fucntionNumber) {
        FucntionNumber = fucntionNumber;
    }

    public Spanned getSpannedText() {
        return spannedText;
    }

    public void setSpannedText(Spanned spannedText) {
        this.spannedText = spannedText;
    }

    public static class Builder {
        private String heading;
        private boolean isShowCloseButton;
        private Drawable drawableIcon;
        private String description;
        private String howItWorkHeader;
        private String howItWorkDescription;
        private String howItWorkDescription2;
        private String howItWorkDescription3;
        private Spanned spannedText;
        private String note;
        private String note2;
        private String negativeButtonText;
        private String positiveButtonText;
        private GenericDialogClickListener genericDialogClickListener;
        private int FucntionNumber;

        public Builder() {
        }

        public Builder setHeading(String heading) {
            this.heading = heading;
            return this;
        }


        public Builder setShowCloseButton(boolean showCloseButton) {
            isShowCloseButton = showCloseButton;
            return this;
        }

        public Builder setDrawableIcon(Drawable drawableIcon) {
            this.drawableIcon = drawableIcon;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setHowItWorkHeader(String howItWorkHeader) {
            this.howItWorkHeader = howItWorkHeader;
            return this;
        }

        public Builder setHowItWorkDescription(String howItWorkDescription) {
            this.howItWorkDescription = howItWorkDescription;
            return this;
        }

        public Builder setHowItWorkHeader2(String howItWorkHeader) {
            this.howItWorkHeader = howItWorkHeader;
            return this;
        }

        public Builder setHowItWorkDescription2(String howItWorkDescription2) {
            this.howItWorkDescription2 = howItWorkDescription2;
            return this;
        }

        public Builder setHowItWorkDescription3(String howItWorkDescription3) {
            this.howItWorkDescription3 = howItWorkDescription3;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }
        public Builder setNote2(String note2) {
            this.note2 = note2;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setGenericDialogClickListener(GenericDialogClickListener genericDialogClickListener) {
            this.genericDialogClickListener = genericDialogClickListener;
            return this;
        }

        public Builder setFucntionNumber(int fucntionNumber) {
            FucntionNumber = fucntionNumber;
            return this;
        }

        public Builder setSpanned(Spanned spanned) {
            spannedText = spanned;
            return this;
        }

        public GenericDialogBuilder build() {
            return new GenericDialogBuilder(heading, isShowCloseButton, drawableIcon, description, howItWorkHeader, howItWorkDescription, howItWorkDescription2, howItWorkDescription3, spannedText, note, note2, negativeButtonText, positiveButtonText, genericDialogClickListener, FucntionNumber);
        }
    }
}
