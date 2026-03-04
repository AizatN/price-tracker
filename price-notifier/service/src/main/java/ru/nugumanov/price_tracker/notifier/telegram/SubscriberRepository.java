package ru.nugumanov.price_tracker.notifier.telegram;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.GetValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Persists Telegram subscriber chat IDs in Consul KV store.
 *
 * <p>Chat IDs are stored under the key {@code price-notifier/subscribers}
 * as a comma-separated string. All read/write errors are logged and do not
 * propagate — the caller receives an empty set on load failure and silently
 * skips the write on save failure.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriberRepository {

    private static final String KEY = "price-notifier/subscribers";

    private final ConsulClient consulClient;

    /**
     * Loads all subscriber chat IDs from Consul KV.
     *
     * @return mutable set of chat IDs, empty if the key is absent or an error occurs
     */
    public Set<String> load() {
        try {
            var response = consulClient.getKVValue(KEY);
            GetValue value = response.getValue();
            if (value == null || value.getDecodedValue() == null || value.getDecodedValue().isBlank()) {
                return new HashSet<>();
            }
            return new HashSet<>(Arrays.asList(value.getDecodedValue().split(",")));
        } catch (Exception e) {
            log.error("Failed to load subscribers from Consul: {}", e.getMessage(), e);
            return new HashSet<>();
        }
    }

    /**
     * Saves the current set of subscriber chat IDs to Consul KV.
     *
     * @param chatIds set of chat IDs to persist; an empty set clears the stored value
     */
    public void save(Set<String> chatIds) {
        try {
            consulClient.setKVValue(KEY, String.join(",", chatIds));
        } catch (Exception e) {
            log.error("Failed to save subscribers to Consul: {}", e.getMessage(), e);
        }
    }
}
