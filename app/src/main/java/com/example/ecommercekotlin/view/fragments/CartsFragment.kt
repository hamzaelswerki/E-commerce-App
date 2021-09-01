package com.example.ecommercekotlin.view.fragments

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.adapter.CartsAdapter
import com.example.ecommercekotlin.databinding.FragmentCartsBinding
import com.example.ecommercekotlin.model.entity.Cart2
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.CartViewModel
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*

class CartsFragment : Fragment(),CartsAdapter.OnQuantiyChanged {

      var productId:Int=0
    var listCarts: List<Cart2> = ArrayList<Cart2>()
  lateinit var cartsAdapter:CartsAdapter
    lateinit  var binding: FragmentCartsBinding
        lateinit var cartViewModel: CartViewModel
    lateinit var pDialog: SweetAlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
    binding= FragmentCartsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel= CartViewModel()
        cartsAdapter= CartsAdapter(this)
        pDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)


        cartViewModel.cartsDataObserve()
        cartViewModel.cartsData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.ShimmerRvCheck.startShimmerAnimation()

                }
                Status.SUCCESS -> {
                    binding.ShimmerRvCheck.visibility = View.GONE
                    binding.rvCarts.visibility = View.VISIBLE
                    binding.noCartItem.visibility=View.GONE
                    createAdapter(it.data!!.data)
                    listCarts = it.data.data
                    if (listCarts.size==0) {

                        binding.rvCarts.visibility = View.GONE
                        binding.noCartItem.visibility = View.VISIBLE
                        binding.ShimmerRvCheck.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding.ShimmerRvCheck.startShimmerAnimation()
                    binding.ShimmerRvCheck.visibility = View.VISIBLE
                    binding.rvCarts.visibility = View.GONE
                    showError(it.message)
                }
            }
        })
        moveToDelete()

        binding.button2.setOnClickListener {
            confirmCarts()
        }
        binding.goShop.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_cartsFragment_to_homeFragment)
        }
        cartViewModel.deleteCartData.observe(viewLifecycleOwner, Observer
        {
            cartViewModel.cartsDataObserve()
        })


     cartViewModel.updateCartsData.observe(viewLifecycleOwner, Observer {
         cartViewModel.cartsDataObserve()

     })

    }

    private fun confirmCarts() {
        cartViewModel.confirmCarts().observe(viewLifecycleOwner, Observer {
            when (it.status){
                Status.LOADING->{
                    showProg(true)
                }
                Status.SUCCESS->{
                    showProg(false)
                    showDilog("Added Successfully")
                    cartViewModel.cartsDataObserve()
                    binding.rvCarts.visibility=View.GONE
                    binding.noCartItem.visibility=View.VISIBLE

                }
                Status.ERROR->{
                    showProg(false)
                    showError(it.message)
            }
            }
        })
    }

    fun createAdapter(list: List<Cart2>){
        binding.rvCarts.adapter=cartsAdapter
        binding.rvCarts.layoutManager=LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
         cartsAdapter.setData(list)
        cartsAdapter.notifyDataSetChanged()
}

    fun showDilog(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setContentText(text)
            .show()
    }

    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

    fun moveToDelete() {
        val simpleCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(5,
                ItemTouchHelper.LEFT) { override fun onChildDraw(c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_outline_24)
                        .addSwipeLeftBackgroundColor(R.color.blueLight)
                        .create()
                        .decorate()
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX / 3,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    productId = listCarts.get(viewHolder.adapterPosition).product.id
                    if (direction == ItemTouchHelper.LEFT) {
                       showDialog()
                    }
                }
            }
        ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvCarts)
    }

    fun showDialog() {
        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Are you sure?")
            .setConfirmText("Delete")
            .setCancelText("No")
            .showCancelButton(true)
            .setCancelClickListener { sweetAlertDialog -> sweetAlertDialog.dismiss() }
            .setConfirmClickListener { sDialog ->
                sDialog.dismissWithAnimation()
                cartViewModel.deleteDataObserve(productId)
                showDilog("Deleted!")

            }
            .show()
    }
    fun showProg(isShow: Boolean) {
        pDialog.getProgressHelper().setBarColor(android.graphics.Color.parseColor("#A5DC86"))
        pDialog.setTitleText("Loading")
        pDialog.setCancelable(false)
        if (isShow) {
            pDialog.show()
        } else {
            pDialog.hide()
        }
    }
    override fun onClickedPlusButton(tv: TextView?, productId: Int) {
        var num=tv?.text.toString().toInt()
        num+=1
        cartViewModel.updateCarts(productId,num)
    /*.observe(viewLifecycleOwner, Observer {
            cartViewModel.cartsDataObserve()

        })*/
    }

    override fun onClickedMinusButton(tv: TextView?, productId: Int) {
        var num=tv?.text.toString().toInt()
        num-=1
        cartViewModel.updateCarts(productId,num)
    /*.observe(viewLifecycleOwner, Observer {
            cartViewModel.cartsDataObserve()

        })*/
    }


}