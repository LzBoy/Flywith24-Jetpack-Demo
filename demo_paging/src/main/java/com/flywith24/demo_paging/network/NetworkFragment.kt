package com.flywith24.demo_paging.network

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.flywith24.demo_paging.Api
import com.flywith24.demo_paging.MyAdapter
import com.flywith24.demo_paging.PAGE_SIZE
import com.flywith24.demo_paging.R
import kotlinx.android.synthetic.main.fragment_network.*

/**
 * @author yyz (杨云召)
 * @date   2020/5/9
 * time   10:52
 * description
 */
class NetworkFragment : Fragment(R.layout.fragment_network) {
    private val liveList: LiveData<PagedList<Api.Data>> by lazy {
        MyDataFactory(lifecycleScope).toLiveData(PAGE_SIZE)
    }

    private val mAdapter: MyAdapter by lazy { MyAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = mAdapter
        liveList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })
    }
}