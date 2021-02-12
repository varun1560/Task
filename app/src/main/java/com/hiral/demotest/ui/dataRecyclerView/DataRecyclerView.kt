package com.hiral.demotest.ui.dataRecyclerView

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiral.demotest.R
import com.hiral.demotest.adapter.CardAdapter
import com.hiral.demotest.data.network.responses.ApiResponse
import com.hiral.demotest.databinding.ActivityDataRecyclerViewBinding
import com.hiral.demotest.utils.CenterView
import com.hiral.demotest.utils.Status

class DataRecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityDataRecyclerViewBinding
    private lateinit var viewModel: DataRecyclerViewModel
    private lateinit var cardAdapter: CardAdapter
    private lateinit var apiResponse: List<ApiResponse>
    private lateinit var customLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_recycler_view)
        viewModel = ViewModelProvider(this).get(DataRecyclerViewModel::class.java)

        setObservers()
    }

    private fun setObservers() {
        viewModel.fetchUsers1().observe(this, Observer {
            it.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.pbData.visibility = View.GONE
                        Log.d("Sucess ", it.data?.get(0)?.userId ?: "No Data")
                        it.data?.let {
                            setAdapters(it)
                        }
                    }
                    Status.LOADING -> {
                        binding.pbData.visibility = View.VISIBLE
                        Log.d("Loading ", "Data is  Loading")
                    }
                    Status.ERROR -> {
                        binding.pbData.visibility = View.GONE
                        Log.d("Error ", it.message)
                    }
                    else -> {
                        binding.pbData.visibility = View.GONE
                        Log.d("Error ", "Something went wrong")
                    }
                }
            }
        })
    }

    private fun setAdapters(resource: List<ApiResponse>) {
        customLayoutManager = CenterView(this@DataRecyclerView, LinearLayoutManager.HORIZONTAL, false)

        apiResponse = resource
        cardAdapter = CardAdapter(this, apiResponse, object : CardAdapter.OnItemClickListener {
            override fun onSelected(position: Int) {
                //CenterView(this@DataRecyclerView, position)
                //cardAdapter.notifyDataSetChanged()
                moveToPosition(position)
            }
        })
        binding.rvData.apply {
            layoutManager=customLayoutManager
            adapter = cardAdapter
        }
    }

    private fun moveToPosition(position:Int){
        customLayoutManager.scrollToPositionWithOffset(position,0)
    }
}