package com.mtb.foodorderreview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.homeview.OrderListViewAdapter;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.ICartFoodDetailFromAPI;
import com.mtb.foodorderreview.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    Context context;
    ListView orders_listview;
    List<CartFood> list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        context = getContext();

        initialization(view);
        setOrderListV(view);
        return view;
    }

    private void initialization(View view) {
        orders_listview = view.findViewById(R.id.orders_listview);

        list = new ArrayList<>();
    }

    private RestaurantFood getRestFoodById(int id) {
        RestaurantFood f = new RestaurantFood(id, "Sample food " + id, "Sample description", Utils.URL_SAMPLE_IMAGE,
                15000);
        return f;
    }

    private Restaurant getRestaurantById(int id) {
        Restaurant f = new Restaurant(id, "Restaurant name " + id, Utils.URL_SAMPLE_IMAGE, "Restaurant address");
        return f;
    }

    private CartFood getCardFoodByFoodId(int id, int quantity) {
        CartFood f = new CartFood(getRestFoodById(id), quantity);

        return f;
    }

    private List<CartFood> getCardFoodsByDetail(List<ICartFoodDetailFromAPI> cartFoodDetails) {
        List<CartFood> f = new ArrayList<>();

        for (ICartFoodDetailFromAPI c : cartFoodDetails) {
            CartFood cartFood = getCardFoodByFoodId(c.foodId(), c.quantity());
            f.add(cartFood);
        }

        return f;
    }

    private Order makeOrder(int id, int restaurantId, int state, String createdAt, List<ICartFoodDetailFromAPI> cartFoodDetails, int finalPrice) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        List<CartFood> cartFood = getCardFoodsByDetail(cartFoodDetails);

        Order o = null;
        try {
            o = new Order(id,
                    restaurant,
                    Utils.dateParse(createdAt),
                    cartFood,
                    Order.STATE.getState(state),
                    finalPrice);
        } catch (ParseException e) {
        }

        return o;
    }

    private void setOrderListV(View view) {
        Order[] l = {
                makeOrder(1,
                        1,
                        2,
                        "12/2/2023",
                        new ArrayList<ICartFoodDetailFromAPI>() {
                            {
                                add(new ICartFoodDetailFromAPI() {
                                    @Override
                                    public int foodId() {
                                        return 1;
                                    }

                                    @Override
                                    public int quantity() {
                                        return 2;
                                    }
                                });
                            }
                        },
                        35000),
                makeOrder(2,
                        2,
                        4,
                        "11/2/2023",
                        new ArrayList<ICartFoodDetailFromAPI>() {
                            {
                                add(new ICartFoodDetailFromAPI() {
                                    @Override
                                    public int foodId() {
                                        return 1;
                                    }

                                    @Override
                                    public int quantity() {
                                        return 2;
                                    }
                                });
                                add(new ICartFoodDetailFromAPI() {
                                    @Override
                                    public int foodId() {
                                        return 2;
                                    }

                                    @Override
                                    public int quantity() {
                                        return 1;
                                    }
                                });
                            }
                        },
                        45000),
        };

        OrderListViewAdapter adapter = new OrderListViewAdapter(context, Arrays.asList(l));

        orders_listview.setAdapter(adapter);
    }
}