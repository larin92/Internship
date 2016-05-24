package com.larin92.testtasks.internship;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.larin92.testtasks.internship.adapters.CardsListAdapter;
import com.larin92.testtasks.internship.adapters.CardsRecyclerAdapter;
import com.melnykov.fab.FloatingActionButton;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CardsFragment extends Fragment {

    private static final String TAG = "CardsFragment";
    private static final String ARG_TAB_NUMBER = "tab-number";
    private int mTab;
    private CardsRecyclerAdapter mRecyclerAdapter;
    private CardsListAdapter mCardsListAdapter;
    private OnListFragmentInteractionListener mListener;
    @Bind(R.id.pull_to_refresh)
    PullToRefreshView mPullToRefreshView;

    final int REFRESH_DELAY = 2000;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CardsFragment() {
    }

    public static CardsFragment newInstance(int tabNumber) {
        CardsFragment fragment = new CardsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_NUMBER, tabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab = getArguments().getInt(ARG_TAB_NUMBER);
        switch (mTab) {
            case 0:
                mRecyclerAdapter = new CardsRecyclerAdapter(getActivity(), App.getInWork(), mListener);
                break;
            case 1:
                mRecyclerAdapter = new CardsRecyclerAdapter(getActivity(), App.getDone(), mListener);
                break;
            case 2:
                mCardsListAdapter = new CardsListAdapter(getActivity(), App.getWaiting(), mListener);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);
        ButterKnife.bind(this, view);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        Context context = view.getContext();
        pullToRefresh(context);

        mTab = getArguments().getInt(ARG_TAB_NUMBER);
        if (mTab == 2) {
            ListView listView = (ListView) view.findViewById(R.id.listview);
            listView.setAdapter(mCardsListAdapter);
            //haven't found a way to fix
            //maybe lib doesn't work well with listview
            mPullToRefreshView.setEnabled(false);
            fab.attachToListView(listView);
            Log.v(TAG, "onCreateView CARDS LIST " + mTab);
        } else {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_cards);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mRecyclerAdapter);
            refreshFix(recyclerView, layoutManager);
            fab.attachToRecyclerView(recyclerView);
            Log.v(TAG, "onCreateView CARDS RECYCLER " + mTab);
        }
        return view;
    }

    private void pullToRefresh(Context context) {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.bird);
        mp.setLooping(true);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mp.start();
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp.pause();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

    private void refreshFix(final RecyclerView recyclerView, final LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition();
                mPullToRefreshView.setEnabled(firstVisibleItem == 0);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        //Update argument type and name
        void onListFragmentInteraction(CardModel item);
    }
}
