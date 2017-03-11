package com.burning.iplay.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.comment.Comment;

/**
 * @author Kiven
 * @time 2017-3-7  15:14
 * Email f842728368@163.com
 * @desc 负责评论中的子评论的View创建
 */
public class SubFloorFactory {
    /**
     * 直接显示评论
     *
     * @param comment
     * @param index
     * @param group   @return
     */
    public View buildSubFloor(Comment comment, int index, ViewGroup group) {
        // to retrieve a LayoutInflater for inflating layout resources in this context
        LayoutInflater inflater = (LayoutInflater) group.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 加载布局文件
        View view = inflater.inflate(R.layout.item_comment_sub, null);
        RelativeLayout show = (RelativeLayout) view.findViewById(R.id.show_sub_floor_content);
        TextView hide_text = (TextView) view.findViewById(R.id.hide_text);


        show.setVisibility(View.VISIBLE);
        hide_text.setVisibility(View.GONE);
        TextView floorNum = (TextView) view.findViewById(R.id.sub_floor_num);
        TextView username = (TextView) view.findViewById(R.id.sub_floor_username);
        TextView content = (TextView) view.findViewById(R.id.sub_floor_content);
        floorNum.setText(String.valueOf(index + 1));
        username.setText(comment.getUser().getNickname());
        content.setText(comment.getContent());
        return view;
    }

    /**
     * 不显示评论，显示加载更多
     *
     * @param comment
     * @param group
     * @return
     */
    public View buildSubHideFloor(Comment comment, ViewGroup group) {
        LayoutInflater inflater = (LayoutInflater) group.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_comment_sub, null);
        RelativeLayout show = (RelativeLayout) view.findViewById(R.id.show_sub_floor_content);
        TextView hide_text = (TextView) view.findViewById(R.id.hide_text);
        show.setVisibility(View.GONE);
        hide_text.setVisibility(View.VISIBLE);
        return view;
    }
}
