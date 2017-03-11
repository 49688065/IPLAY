package com.burning.iplay.ui.comment;

import android.os.Build;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseActivity;

/**
 * @author Kiven
 * @time 2017-3-7  9:37
 * Email f842728368@163.com
 * @desc 评论界面的Activity，负责显示Fragment
 */
public class CommentActivity extends BaseActivity {

    public static final String COMMENT_DOC_ID = "comment_doc_id";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_commom;
    }

    @Override
    protected void initUI() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
        String docId = getIntent().getStringExtra(COMMENT_DOC_ID);
        CommentFragment commentFragment = new CommentFragment();
        commentFragment.setDocId(docId);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_fl, commentFragment).commit();
    }

    @Override
    protected void requestPermissionSucceed() {
    }
}
