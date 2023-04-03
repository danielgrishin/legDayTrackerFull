package com.example.legdaytrackerBetter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.legdaytrackerBetter.R

class LegDayAdapter(private val items: List<DisplayLegDay>) : RecyclerView.Adapter<LegDayAdapter.ViewHolder>(){
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val legDayDate: TextView
        val legDaySquat: TextView
        val legDayLegExtension: TextView
        val legDayLegCurl: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            legDayDate=itemView.findViewById(R.id.fragDate)
            legDaySquat = itemView.findViewById(R.id.inpSquat)
            legDayLegExtension = itemView.findViewById(R.id.inpLegExt)
            legDayLegCurl = itemView.findViewById(R.id.inpLegCurl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.leg_day_fragment, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on views and data model
        holder.legDayDate.text=item.date
        holder.legDaySquat.text = item.squat.toString()
        holder.legDayLegExtension.text = item.legExtension.toString()
        holder.legDayLegCurl.text = item.legCurl.toString()}

    override fun getItemCount(): Int {
        return items.size
    }
}