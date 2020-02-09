package org.adynatos.feel_at_now;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.adynatos.feel_at_now.converters.DateConverter;
import org.adynatos.feel_at_now.converters.FeelConverter;

import java.util.Calendar;
import java.util.List;

public class GraphFragment extends Fragment {
    private FeelDataViewModel mModel;
    private LineGraphSeries<DataPoint> mSeries;

    public GraphFragment() {
        // Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mModel = ViewModelProviders.of(this.getActivity()).get(FeelDataViewModel.class);
        //model.get().observe(
        //        this,
        //        new Observer<List<FeelData>>() {
        //            @Override
        //            public void onChanged(@Nullable final List<FeelData> data) {
        //                mCached = data;
        //            }
        //        }
        //);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph, container, false);

        final GraphView mGraph = view.findViewById(R.id.details);
        mGraph.getViewport().setScalable(true);
        mGraph.getViewport().setScrollable(true);
        mGraph.getViewport().setScalableY(true);
        mGraph.getViewport().setScrollableY(true);
        mGraph.getViewport().setYAxisBoundsManual(true);
        mGraph.getViewport().setMinY(-2);
        mGraph.getViewport().setMaxY(2);
        mGraph.getViewport().setXAxisBoundsManual(true);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        mGraph.getViewport().setMinX(new DateConverter().toTimestamp(c.getTime()));
        mGraph.getViewport().setMaxX(new DateConverter().toTimestamp(Calendar.getInstance().getTime()));
        mGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this.getContext()));
        mGraph.getGridLabelRenderer().setHumanRounding(false);

        mSeries = new LineGraphSeries<>();
        mGraph.addSeries(mSeries);

        AsyncTask.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        List < FeelData > list = mModel.get();
                        if (null != list) {
                            for (FeelData feelData : list) {
                                final DataPoint point = new DataPoint(feelData.date(), new FeelConverter().toInt(feelData.feel()));
                                mSeries.appendData(point, false, list.size());
                            }
                        }
                    }
                }
        );

        return view;
    }
}
