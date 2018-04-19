package com.liuchuanzheng.greendaotest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liuchuanzheng.greendaotest.bean.Student;
import com.liuchuanzheng.greendaotest.greendao.DaoMaster;
import com.liuchuanzheng.greendaotest.greendao.DaoSession;
import com.liuchuanzheng.greendaotest.greendao.StudentDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private Button delete;
    private Button update;
    private Button query;
    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //第七步 make project
        //第八步 得到session
        initGreenDao();
    }

    private void initGreenDao() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "liuchuanzheng-db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    private void initView() {
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        query = findViewById(R.id.query);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第九步 通过session得到对应实体类的dao
                StudentDao studentDao = mDaoSession.getStudentDao();
                //第一个是id值，因为是自增长所以不用传入
                Student student = new Student();
                student.setId(1);
                student.setName("张三");
                student.setAge(10);
                //第十部 通过dao执行增删改查方法
                studentDao.insert(student);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDao studentDao = mDaoSession.getStudentDao();
                studentDao.deleteByKey((long) 1);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDao studentDao = mDaoSession.getStudentDao();
                Student student = new Student();
                student.setId(1);
                student.setName("李四");
                student.setAge(10);
                studentDao.update(student);
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDao studentDao = mDaoSession.getStudentDao();
                QueryBuilder<Student> studentQueryBuilder = studentDao.queryBuilder();
                studentQueryBuilder.where(StudentDao.Properties.Name.eq("张三"));
                List<Student> list = studentQueryBuilder.list();
                if (list.size()>0){
                    Toast.makeText(MainActivity.this,"查到了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
