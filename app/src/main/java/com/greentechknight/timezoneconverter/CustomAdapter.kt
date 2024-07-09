package com.greentechknight.timezoneconverter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import java.util.*


public class CustomAdapter(context: Context?, resource: Int, var allCodes: List<String>) :
    ArrayAdapter<Any?>(context!!, resource, allCodes), Filterable {
    var originalCodes: List<String>
    var filter: StringFilter? = null

    init {
        originalCodes = allCodes
    }

    override fun getCount(): Int {
        return allCodes.size
    }

    override fun getItem(position: Int): Any? {
        return allCodes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class StringFilter : Filter() {
        protected override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            val filterString = constraint.toString().lowercase(Locale.getDefault())

            val list = originalCodes
            val count = list.size
            val nlist = ArrayList<String>(count)
            var filterableString: String
            if(constraint != null) {
                for (i in 0 until count) {
                    filterableString = list[i]
                    if (filterableString.lowercase(Locale.getDefault()).contains(filterString)) {
                        nlist.add(filterableString)
                    }
                }
                results.values = nlist
                results.count = nlist.size
            }
            else{
                nlist.add("Enter Search Text")
                results.values = nlist
                results.count = 1
            }
            return results
        }

        protected override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            if(results != null && results.count>0) {
                allCodes = results.values as List<String>
                notifyDataSetChanged()
            }else{
                notifyDataSetInvalidated()
            }

        }
    }

    override fun getFilter(): Filter {
        return StringFilter()
    }
}
