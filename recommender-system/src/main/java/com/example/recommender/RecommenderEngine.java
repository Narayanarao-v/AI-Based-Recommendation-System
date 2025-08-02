package com.example.recommender;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import java.io.File;
import java.util.List;

public class RecommenderEngine {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/dataset.csv");
        DataModel model = new FileDataModel(file);

        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        int userID = 1;
        List<RecommendedItem> recommendations = recommender.recommend(userID, 3);

        System.out.println("Recommendations for User ID " + userID + ":");
        for (RecommendedItem item : recommendations) {
            System.out.println("Item: " + item.getItemID() + ", Score: " + item.getValue());
        }
    }
}
