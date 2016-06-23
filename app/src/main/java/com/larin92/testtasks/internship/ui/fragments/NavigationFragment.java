package com.larin92.testtasks.internship.ui.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.contract.NavigationContract;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.presenter.NavigationPresenter;
import com.larin92.testtasks.internship.ui.adapters.CardsRecyclerAdapter;
import com.melnykov.fab.FloatingActionButton;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NavigationFragment extends Fragment implements NavigationContract.View {

    private static final String ARG_TAB_NUMBER = "tab-number";
    private static final int REFRESH_DELAY = 2000;
    private int mTab;
    private CardsRecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private OnListFragmentInteractionListener mListener;
    private Unbinder mUnbinder;
    private MediaPlayer mp;
    private NavigationPresenter mNavigationPresenter;

    @BindView(R.id.pull_to_refresh)
    PullToRefreshView mPullToRefreshView;

    @BindView(R.id.recycler_cards)
    RecyclerView mRecyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NavigationFragment() {
    }

    public static NavigationFragment newInstance(int tabNumber) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_NUMBER, tabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab = getArguments().getInt(ARG_TAB_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mTab = getArguments().getInt(ARG_TAB_NUMBER);
        Context context = view.getContext();
        mp = MediaPlayer.create(context, R.raw.bird);
        mLinearLayoutManager = new LinearLayoutManager(context);
        pullToRefresh();
        refreshFix();

        mNavigationPresenter = new NavigationPresenter(mTab);
        mNavigationPresenter.attachView(this);
        mNavigationPresenter.showBackup();
        //mNavigationPresenter.receiveData(true);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPullToRefreshView.clearAnimation();
    }

    @Override
    public Context getViewContext() {
        return this.getContext();
    }

    private void pullToRefresh() {
        mp.setLooping(true);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mNavigationPresenter.update();
                mp.start();
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isDetached() || isRemoving() || !isAdded()) {
                            return;
                        }
                        mp.pause();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

    @Override
    public void setData(List<CardModel> list) {
        mRecyclerAdapter = new CardsRecyclerAdapter(getActivity(), list, mListener);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void notifyAdapter(List<CardModel> list) {
        mRecyclerAdapter.addCards(list);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRecyclerAdapter != null) {
            return mRecyclerAdapter.getItemCount();
        } else {
            return 0;
        }
    }

    //  if you will scroll via mouse scroll it won't work
    private void refreshFix() {
        mRecyclerView.addOnScrollListener(recyclerListener);
    }

    RecyclerView.OnScrollListener recyclerListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int firstVisibleItem = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
            mPullToRefreshView.setEnabled(firstVisibleItem == 0);

            int items = mRecyclerView.getAdapter().getItemCount();
            int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (items > 0 && firstVisibleItem != 0) {
                if (lastVisibleItem == (mNavigationPresenter.getOffset() - 1)) {
                    mNavigationPresenter.receiveData();
                }
            }
        }
    };

    @Override
    public void showRefresh() {
        mPullToRefreshView.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        mPullToRefreshView.setRefreshing(false);
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
        mp.release();
        mNavigationPresenter.detachView();
        mRecyclerAdapter = null;
        recyclerListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mp.release();
        mPullToRefreshView.clearAnimation();
        mNavigationPresenter.detachView();
        mUnbinder.unbind();
        mRecyclerAdapter = null;
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
        void onListFragmentInteraction(CardModel item);
    }
}
