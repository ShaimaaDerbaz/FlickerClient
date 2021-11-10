import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flickerclient.databinding.ItemFlickerImageBinding
import com.flickerclient.ui.Utils.RoundedCornersTransformation
import com.flickerclient.ui.model.FlickrImage
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation


class PopularImagesAdapter(
    private val flickerImages: MutableList<FlickrImage>,
    private val context: Context,
    private var listener: PopularItemListener
) : RecyclerView.Adapter<PopularImagesAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemFlickerImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFlickerImageBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return ViewHolder(
            binding
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var imageUrl = flickerImages.get(position).imageUrl
        val transformation: Transformation =
            RoundedCornersTransformation(25, 0)
        Picasso.with(context)
            .load(imageUrl)
            .transform(transformation)
            .into(holder.binding.ivIamge)

        holder.binding.tvTitle.text = flickerImages.get(position).title

        holder.itemView.setOnClickListener {

            if (listener != null) {
                listener.onItemPopularClicked(flickerImages.get(position))
            }

        }
    }

    override fun getItemCount(): Int = flickerImages.size

    fun addItems(flickerImages: List<FlickrImage>) {
        this.flickerImages.addAll(flickerImages)
    }

    fun deleteItems() {
        this.flickerImages.clear()
    }

    interface PopularItemListener {
        fun onItemPopularClicked(image: FlickrImage?)
    }
}
