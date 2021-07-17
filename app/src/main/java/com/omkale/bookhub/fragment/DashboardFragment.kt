package com.omkale.bookhub.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omkale.bookhub.R
import com.omkale.bookhub.adapter.DashboardRecyclerAdapter
import com.omkale.bookhub.model.Book
import com.omkale.bookhub.util.ConnectionManager

class DashboardFragment : Fragment() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var btnCheckInternet:Button
    val bookList = arrayListOf(
        "Book1",
        "Book2",
        "Book3",
        "Book4",
        "Book5",
        "Book6",
        "Book7",
        "Book8",
        "Book9",
        "Book10",
    )

    val bookInfoList = arrayListOf<Book>(
        Book(
            "Daring Greatly",
            "Brene Brown",
            "Rs.299",
            "4.5",
            R.drawable.daring_greatly
        ),
        Book(
            "Eat That Frog First",
            "Brian Tracy",
            "Rs.299",
            "4.5",
            R.drawable.eat_that_frog
        ),
        Book(
            "Failing Forward",
            "John Maxwell",
            "Rs.399",
            "4.5",
            R.drawable.failing_forward
        ),
        Book(
            "Feeling Good",
            "David Burns",
            "Rs.299",
            "4.5",
            R.drawable.feeling_good
        ),
        Book(
            "Fooled",
            "Nassim Taleb",
            "Rs.199",
            "3.5",
            R.drawable.foole
        ),
        Book(
            "How to Win and Influence Friends",
            "Dale Carnegie",
            "Rs.499",
            "4.6",
            R.drawable.how_to_win
        ),
        Book(
            "Perfect Health",
            "Paul Jameniet",
            "Rs.299",
            "4.5",
            R.drawable.perfect_health_diet
        ),Book(
            "Mindsight",
            "Daniel Siegel",
            "Rs.299",
            "4.5",
            R.drawable.mindsight
        ),
        Book(
            "The Paleo Manifesto",
            "John Durant",
            "Rs.99",
            "3.5",
            R.drawable.paleo_manifesto
        ),
    )
    lateinit var recyclerAdapter: DashboardRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        btnCheckInternet=view.findViewById(R.id.btnCheckInternet)
        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)
        layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, bookInfoList)
        recyclerDashboard.adapter = recyclerAdapter
        recyclerDashboard.layoutManager = layoutManager
        recyclerDashboard.addItemDecoration(
            DividerItemDecoration(
                recyclerDashboard.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )
        btnCheckInternet.setOnClickListener{
            if(ConnectionManager().checkConnectivity(activity as Context)){
                //internet avail
                val dialog =AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet connection found")
                dialog.setPositiveButton("Ok"){text,listner->
                    //do nothing
                }
                dialog.setNegativeButton("Cancel"){text,listner->
                    //do nothing
                }
                dialog.create()
                dialog.show()

            }else{
                //internet not avail
                val dialog =AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet connection was not found")
                dialog.setPositiveButton("Ok"){text,listner->
                    //do nothing
                }
                dialog.setNegativeButton("Cancel"){text,listner->
                    //do nothing
                }
                dialog.create()
                dialog.show()

            }
        }
        return view
    }
}