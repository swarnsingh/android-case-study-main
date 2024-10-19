package com.target.targetcasestudy.ui.deal.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DealDetailsFragment : Fragment() {

    private val viewModel: DealDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                    viewLifecycleOwner
                )
            )
            val dealId = arguments?.getString("dealId") ?: "-1"
            if (savedInstanceState == null) {
                viewModel.getDealDetails(id = dealId)
            }
            setContent {
                val response = viewModel.dealStateFlow.collectAsStateWithLifecycle()
                DealDetailScreen(
                    response = response.value,
                    onBackClick = { findNavController().popBackStack() },
                    onAddToCartClick = {},
                    onRetryClick = { viewModel.getDealDetails(id = dealId) }
                )
            }
        }
    }
}
