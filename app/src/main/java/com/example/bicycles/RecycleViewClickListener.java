package com.example.bicycles;

import android.view.View;

public interface RecycleViewClickListener {
    // Role is to facilitate the onClick event listeners of items in a RecylerView
    public void recycleViewOnClick(View view, int position);
}
