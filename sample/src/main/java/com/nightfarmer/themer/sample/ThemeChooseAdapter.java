package com.nightfarmer.themer.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nightfarmer.themer.Themer;

/**
 * Created by zhangfan on 16-9-8.
 */
public class ThemeChooseAdapter extends RecyclerView.Adapter<ThemeChooseAdapter.ThemeItemHolder> {


    @Override
    public ThemeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_choose_item, parent, false);
        return new ThemeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ThemeItemHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Themes theme = Themes.values()[position];
        holder.theme = theme.getTheme();
        holder.tv_title.setText(theme.getTitle());
        if (Themer.INSTANCE.getStyle() == theme.getTheme()) {
            holder.theme_choose_btn.setChecked(true);
            holder.theme_choose_btn.setTextColor(Themer.INSTANCE.getColor(context, R.attr.colorPrimary));
            holder.theme_choose_btn.setText("使用中");
            holder.theme_color_dot.setText("√");
        } else {
            holder.theme_choose_btn.setChecked(false);
            holder.theme_choose_btn.setTextColor(ContextCompat.getColor(context, R.color.secondary_text));
            holder.theme_choose_btn.setText("使用");
            holder.theme_color_dot.setText("");
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(ContextCompat.getColor(context, theme.getColorRes()));
        gradientDrawable.setCornerRadius(40f);
        holder.theme_color_dot.setBackgroundDrawable(gradientDrawable);
    }

    @Override
    public int getItemCount() {
        return Themes.values().length;
    }

    public class ThemeItemHolder extends RecyclerView.ViewHolder {
        int theme;

        TextView theme_color_dot;
        TextView tv_title;
        RadioButton theme_choose_btn;

        public ThemeItemHolder(View itemView) {
            super(itemView);
            theme_color_dot = (TextView) itemView.findViewById(R.id.theme_color_dot);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            theme_choose_btn = (RadioButton) itemView.findViewById(R.id.theme_choose_btn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick();
                }
            });
            theme_choose_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick();
                }
            });
        }

        private void onItemClick() {
            Themer.INSTANCE.setTheme((Activity) itemView.getContext(), theme, null);
        }
    }
}
