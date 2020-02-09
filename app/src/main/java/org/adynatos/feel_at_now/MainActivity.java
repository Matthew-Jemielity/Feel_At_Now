package org.adynatos.feel_at_now;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Class[] pipeline = {
            ButtonsFragment.class,
            GraphFragment.class
    };
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        try {
            transaction.replace(
                    R.id.fragment,
                    (Fragment) pipeline[ current % 2 ].newInstance()
            );
            transaction.commit();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        final FloatingActionButton graph = findViewById(R.id.switcher);
        graph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                ++current;

                try {
                    transaction.replace(
                            R.id.fragment,
                            (Fragment) pipeline[ current % 2 ].newInstance()
                    );
                    transaction.commit();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
