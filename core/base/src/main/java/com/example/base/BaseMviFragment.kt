package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

interface MviState

interface MviAction

interface MviEffect


abstract class BaseMviFragment<VB : ViewBinding, S : MviState, A : MviAction, E : MviEffect> :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected val toolbarTitle: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = createBindingInstance(inflater, container)
        toolbarTitle?.let {
            activity?.title = toolbarTitle
            activity?.actionBar?.title = toolbarTitle
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun render(state: S)
    abstract fun render(effect: E)

    abstract fun observers()

    protected fun View.show() {
        visibility = View.VISIBLE
    }

    protected fun View.hide() {
        visibility = View.GONE
    }

    protected fun toast(text: String, isLong: Boolean = false) {
        val length = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(requireContext(), text, length).show()
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        state: Lifecycle.State = Lifecycle.State.STARTED,
        collector: FlowCollector<T>,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                flow.collect(collector)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun createBindingInstance(inflater: LayoutInflater, container: ViewGroup?): VB {
        val vbType = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val vbClass = vbType as Class<VB>
        val method = vbClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, inflater, container, false) as VB
    }
}