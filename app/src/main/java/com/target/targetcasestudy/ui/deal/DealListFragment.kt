package com.target.targetcasestudy.ui.deal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.DealItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.target.targetcasestudy.data.Result
import com.target.targetcasestudy.data.collectUntilSuccessOrError
import com.target.targetcasestudy.util.collectWithLifecycle

@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val viewModel: DealListViewModel by viewModels()
    private val adapter = DealItemAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)

        view.findViewById<RecyclerView>(R.id.recycler_view).layoutManager =
            LinearLayoutManager(requireContext())
        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDeals()

        viewModel.dealsStateFlow.collectWithLifecycle(this) { result ->
            when (result) {
                is Result.Error -> {

                }
                is Result.Loading -> {

                }
                is Result.Success -> {
                    adapter.updateDeals(result.data)
                }
            }
        }
    }
}
