package com.hiralpractical.Adapter

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings.Global.getString
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.hiralpractical.Activity.RegisterItemListener
import com.hiralpractical.Activity.RegisterListActivity
import com.hiralpractical.Database.Table.RegisterTable
import com.hiralpractical.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.item_row_register.view.*


class RegisterListAdapter(private val listener: RegisterItemListener,val context:Context) : RecyclerView.Adapter<RegisterListAdapter.RegisterListViewHolder>() {
    private var getAllList: ArrayList<RegisterTable> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegisterListViewHolder {
        return RegisterListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_register, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: RegisterListViewHolder, position: Int) {
        val data = getAllList[position]
       // holder.tvTitle.text = data.title
        holder.tvName.text = data.name
        holder.tvEmail.text = data.email
        holder.tvphone.text = data.phone
        holder.iv_edit.setOnClickListener {
            val data1 = getAllList[position]
            val dialog = Dialog(context,R.style.MyDialog)
            dialog.window?.requestFeature(Window.FEATURE_NO_TITLE) // if you have blue line on top of your dialog, you need use this code
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            val width = ViewGroup.LayoutParams.FILL_PARENT
            val height = ViewGroup.LayoutParams.FILL_PARENT
            dialog.window?.setLayout(width, height)
            dialog.setContentView(R.layout.dialog_custom_layout)
            val et_d_name = dialog.findViewById(R.id.et_d_name) as TextInputEditText
            val et_d_email = dialog.findViewById(R.id.et_d_email) as TextInputEditText
            val et_d_phone = dialog.findViewById(R.id.et_d_phone) as TextInputEditText
            val btn_update = dialog.findViewById(R.id.btn_update) as AppCompatButton
            val btn_cancel = dialog.findViewById(R.id.btn_cancel) as AppCompatButton
            et_d_name.text = Editable.Factory.getInstance().newEditable(data1.name)
            et_d_email.text =Editable.Factory.getInstance().newEditable(data1.email)
            et_d_phone.text = Editable.Factory.getInstance().newEditable(data1.phone)
            btn_update.setOnClickListener {
                if(et_d_name.text.toString().trim().isEmpty()){
                    Toast.makeText(context,"Please Enter Name", Toast.LENGTH_LONG).show()
                }else if(et_d_email.text.toString().trim().isEmpty()){
                    Toast.makeText(context,"Please Enter Email", Toast.LENGTH_LONG).show()
                }else if(et_d_phone.text.toString().trim().isEmpty()){
                    Toast.makeText(context,"Please Enter Phone", Toast.LENGTH_LONG).show()
                }else{
                    val registerTable = RegisterTable()
                    registerTable.id= data1.id
                    registerTable.name= et_d_name.text.toString().trim()
                    registerTable.email= et_d_email.text.toString().trim()
                    registerTable.phone= et_d_phone.text.toString().trim()

                    getAllList.get(position).name=et_d_name.text.toString().trim()
                    getAllList.get(position).email=et_d_email.text.toString().trim()
                    getAllList.get(position).phone=et_d_phone.text.toString().trim()
                    Toast.makeText(context,"Update Successfully", Toast.LENGTH_LONG).show()
                    getAllList.clear()
                    listener.ItemClicked(registerTable,"Edit")
                    //notifyItemChanged(position,getAllList.size);
                   // notifyItemRangeChanged(position,getAllList.size)

                   notifyDataSetChanged()


                }
                dialog.dismiss()

            }
            btn_cancel.setOnClickListener {

                dialog.dismiss()
            }
            dialog.show()

          //  notifyDataSetChanged()


        }


        holder.iv_delete.setOnClickListener {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Register")
                builder.setMessage("Arre you Sure You want To delete")
                //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                    getAllList.removeAt(position)


                    if(getAllList.size>0) {
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, getAllList.size)
                        notifyDataSetChanged()
                    }else{
                        notifyDataSetChanged()
                    }

                    listener.ItemClicked(data,"delete")
                    getAllList.clear()


                    Toast.makeText(context,"Delete Successfully", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                    //notifyDataSetChanged()

                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    dialog.dismiss()

                }


                builder.show()


        }

//        holder.itemView.setOnClickListener {
//            if(data.approvalStatus != "Pending")
//            {
//                listener.jobClicked(joblist[position])
//            }
//        }

    }

    fun replaceAll(registerTable: List<RegisterTable>) {
        getAllList.clear()
        getAllList.addAll(registerTable)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addall(dataList: ArrayList<RegisterTable>) {
        getAllList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return getAllList.size
    }

    class RegisterListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.tvName
        val tvEmail: TextView = view.tvEmail
        val tvphone: TextView = view.tvphone
        val iv_edit: ImageView = view.iv_edit
        val iv_delete: ImageView = view.iv_delete
    }
}