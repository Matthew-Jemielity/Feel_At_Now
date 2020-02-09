package org.adynatos.feel_at_now;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ButtonsFragment extends Fragment {
    private FeelDataViewModel mModel;

    public ButtonsFragment() {
        // Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mModel = ViewModelProviders.of(this.getActivity()).get(FeelDataViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons, container, false);

        final ImageButton happyButton = view.findViewById(R.id.happy);
        final ImageButton mehButton = view.findViewById(R.id.meh);
        final ImageButton sadButton = view.findViewById(R.id.sad);

        happyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonAction(Feel.HAPPY);
            }
        });
        mehButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonAction(Feel.MEH);
            }
        });
        sadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonAction(Feel.SAD);
            }
        });

        return view;
    }

    private void buttonAction(final Feel action) {
        mModel.put(new FeelData(Calendar.getInstance().getTime(), action));
    }
}
