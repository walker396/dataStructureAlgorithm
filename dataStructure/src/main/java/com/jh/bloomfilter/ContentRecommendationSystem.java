package com.jh.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author johnny
 */
public class ContentRecommendationSystem {
    // Bloom filter to track viewed content efficiently
    private final BloomFilter<String> viewedContentFilter;

    // Simulated content repository
    private final List<String> allContent;

    /**
     * Constructor to initialize the recommendation system
     * @param expectedInsertions Estimated number of viewed items
     * @param falsePositiveProbability Acceptable false positive rate
     */
    public ContentRecommendationSystem(int expectedInsertions, double falsePositiveProbability) {
        // Create a Bloom filter with specified parameters
        this.viewedContentFilter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                expectedInsertions,
                falsePositiveProbability
        );

        // Generate a large simulated content repository
        this.allContent = generateContentCatalog(10000);
    }

    /**
     * Generate a simulated content catalog
     * @param size Number of content items to generate
     * @return List of content items
     */
    private List<String> generateContentCatalog(int size) {
        List<String> content = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            content.add("Content_" + i);
        }
        return content;
    }

    /**
     * Simulate user viewing content and adding to viewed filter
     * @param contentId ID of the viewed content
     */
    public void markContentAsViewed(String contentId) {
        viewedContentFilter.put(contentId);
    }

    /**
     * Get recommendations, excluding previously viewed content
     * @param recommendationCount Number of recommendations to generate
     * @return List of recommended content
     */
    public List<String> getRecommendations(int recommendationCount) {
        return allContent.stream()
                // Filter out previously viewed content using Bloom filter
                .filter(content -> !viewedContentFilter.mightContain(content))
                // Randomly select recommendations
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Random random = new Random();
                            return random.ints(0, list.size())
                                    .distinct()
                                    .limit(recommendationCount)
                                    .mapToObj(list::get)
                                    .collect(Collectors.toList());
                        }
                ));
    }

    /**
     * Demonstrate Bloom filter efficiency and usage
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Initialize recommendation system
        ContentRecommendationSystem recommender = new ContentRecommendationSystem(
                5000,   // Expected number of viewed items
                0.01    // 1% false positive probability
        );

        // Simulate viewing some content
        List<String> viewedContent = List.of(
                "Content_42", "Content_123", "Content_256",
                "Content_789", "Content_1024"
        );
        viewedContent.forEach(recommender::markContentAsViewed);

        // Get recommendations
        List<String> recommendations = recommender.getRecommendations(5);

        System.out.println("Recommendations:");
        recommendations.forEach(System.out::println);

    }
}
