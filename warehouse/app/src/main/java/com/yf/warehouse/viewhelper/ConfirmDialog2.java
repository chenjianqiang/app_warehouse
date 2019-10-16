package com.yf.warehouse.viewhelper;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.yf.warehouse.R;


/**
 * @author cjq
 */
public class ConfirmDialog2{
    private View mView;
    private Dialog dialog;
    private Context context;
    private TextView btnCancel;
    private TextView btnConfirm;
    private TextView btnNeutral;
    private TextView tvTitle;
    private TextView tvMsg;
    private View viewDivider,viewDivider2,viewDivider3;
    private DialogInterface.OnClickListener onClickListener;
    private int width = WindowManager.LayoutParams.WRAP_CONTENT;
    private int height = WindowManager.LayoutParams.WRAP_CONTENT;

    public ConfirmDialog2(@NonNull Context context) {
        dialog = new Dialog(context, R.style.Theme_dialog);
        this.context = context;
        init();
    }

    public ConfirmDialog2(@NonNull Context context, int themeResId) {
        this.context = context;
        dialog = new Dialog(context, themeResId);
        init();
    }

    @SuppressLint("InflateParams")
    private void init() {
        initAllView();
        dialog.setContentView(mView);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
                dialog.dismiss();
            }
        });

        btnNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                }
                dialog.dismiss();
            }
        });
    }

    private void initAllView(){
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.dialog_confirm2,null);
        btnNeutral = mView.findViewById(R.id.btnNeutral);
        btnCancel = mView.findViewById(R.id.btnCancel);
        btnConfirm = mView.findViewById(R.id.btnConfirm);
        tvTitle = mView.findViewById(R.id.tvTitle);
        tvMsg = mView.findViewById(R.id.tvMsg);
        viewDivider = mView.findViewById(R.id.viewDivider);
        viewDivider2 = mView.findViewById(R.id.viewDivider2);
        viewDivider3 = mView.findViewById(R.id.viewDivider3);
    }

    public ConfirmDialog2 setTitle(CharSequence title) {
        tvTitle.setText(title);
        return this;
    }

    public ConfirmDialog2 setMessage(CharSequence message) {
        tvMsg.setText(message);
        return this;
    }

    public ConfirmDialog2 setButton(int witch, CharSequence text) {
        switch (witch) {
            case DialogInterface.BUTTON_POSITIVE:
                btnConfirm.setText(text);
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                btnNeutral.setText(text);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                btnCancel.setText(text);
                break;
            default:
                throw new IllegalArgumentException("No such button!");
        }
        return this;
    }

    public ConfirmDialog2 setWidth(int width) {
        this.width = width;
        return this;
    }

    public ConfirmDialog2 setHeight(int height) {
        this.height = height;
        return this;
    }

    public ConfirmDialog2 setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public Dialog show() {
        boolean noMessage = tvMsg.getText().toString().isEmpty();
        boolean noConfirm = btnConfirm.getText().toString().isEmpty();
        boolean noNeutral = btnNeutral.getText().toString().isEmpty();
        boolean noCancel = btnCancel.getText().toString().isEmpty();
        boolean noButton = noConfirm && noCancel && noNeutral;
        tvMsg.setVisibility(noMessage ? View.GONE : View.VISIBLE);
        btnConfirm.setVisibility(noConfirm ? View.GONE : View.VISIBLE);
        viewDivider3.setVisibility(noConfirm ? View.GONE : View.VISIBLE);
        btnNeutral.setVisibility(noNeutral ? View.GONE : View.VISIBLE);
        viewDivider3.setVisibility(noNeutral ? View.GONE : View.VISIBLE);
        btnCancel.setVisibility(noCancel ? View.GONE : View.VISIBLE);
        viewDivider2.setVisibility(noCancel ? View.GONE : View.VISIBLE);
        viewDivider.setVisibility(noButton ? View.GONE : View.VISIBLE);
        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = width;
            params.height = height;
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(params);
        }
        return dialog;
    }

    public ConfirmDialog2 setButtonTextSize(float sizeSp) {
        btnConfirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
        btnCancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
        btnNeutral.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
        return this;
    }

    public ConfirmDialog2 setOnKeyListener(DialogInterface.OnKeyListener listener) {
        dialog.setOnKeyListener(listener);
        return this;
    }

    public ConfirmDialog2 setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public ConfirmDialog2 setCanceledOnTouchOutside(boolean canceled) {
        dialog.setCanceledOnTouchOutside(canceled);
        return this;
    }

    public ConfirmDialog2 setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
        dialog.setOnDismissListener(dismissListener);
        return this;
    }

    public ConfirmDialog2 setOnCancelListener(DialogInterface.OnCancelListener cancelListener) {
        dialog.setOnCancelListener(cancelListener);
        return this;
    }

    public ConfirmDialog2 setButtonTextColor(int witch, @ColorInt int color) {
        switch (witch) {
            case DialogInterface.BUTTON_POSITIVE:
                btnConfirm.setTextColor(color);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                btnCancel.setTextColor(color);
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                btnNeutral.setTextColor(color);
                break;
            default:
                break;
        }
        return this;
    }
}