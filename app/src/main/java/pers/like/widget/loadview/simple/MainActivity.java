package pers.like.widget.loadview.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import pers.like.widget.loadview.Action;
import pers.like.widget.loadview.LoadView;
import pers.like.widget.loadview.Options;

/**
 * @author Like
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadView loadView = findViewById(R.id.load);
        findViewById(R.id.view1).setOnClickListener(v -> {
            loadView.empty(new Options()
                    .icon(R.drawable.vector_empty)
                    .action(new Action("自定义按钮", v1 -> Toast.makeText(this, "自定义操作", Toast.LENGTH_SHORT).show()))
                    .message("自定义消息"));
        });
        findViewById(R.id.view2).setOnClickListener(v -> {
            loadView.loading();
        });
        findViewById(R.id.view3).setOnClickListener(v -> {
            loadView.content();
        });
        findViewById(R.id.view4).setOnClickListener(v -> {
            loadView.empty();
        });
        findViewById(R.id.view5).setOnClickListener(v -> {
            loadView.error();
        });
        findViewById(R.id.view6).setOnClickListener(v -> {
            loadView.error(new Options()
                    .action(new Action("自定义错误按钮::重试", v1 -> Toast.makeText(this, "重试", Toast.LENGTH_SHORT).show()))
            );
        });
    }

}
