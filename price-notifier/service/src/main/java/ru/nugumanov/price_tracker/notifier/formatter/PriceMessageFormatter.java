package ru.nugumanov.price_tracker.notifier.formatter;

import org.springframework.stereotype.Component;
import ru.nugumanov.price_common.model.PriceReportModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class PriceMessageFormatter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final DecimalFormat PRICE_FORMAT;

    static {
        var symbols = new DecimalFormatSymbols(Locale.forLanguageTag("ru"));
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator('.');
        PRICE_FORMAT = new DecimalFormat("#,##0.00", symbols);
    }

    public String format(List<PriceReportModel> reports) {
        var sb = new StringBuilder();
        sb.append("\uD83D\uDCCA *\u0421\u0432\u043E\u0434\u043A\u0430 \u0446\u0435\u043D*\n\n");

        var grouped = reports.stream()
                .collect(Collectors.groupingBy(PriceReportModel::getSourceName, Collectors.toList()));

        for (var entry : grouped.entrySet()) {
            sb.append("\uD83C\uDFEC *").append(escapeMarkdown(entry.getKey())).append("*\n\n");

            for (var report : entry.getValue()) {
                sb.append("\uD83C\uDFF7 ").append(escapeMarkdown(report.getProductName())).append("\n");
                sb.append("   \u0426\u0435\u043D\u0430: ").append(PRICE_FORMAT.format(report.getCurrentPrice())).append(" \u20BD\n");
                if (report.getLastUpdated() != null) {
                    sb.append("   \u041E\u0431\u043D\u043E\u0432\u043B\u0435\u043D\u043E: ").append(report.getLastUpdated().format(DATE_FORMATTER)).append("\n");
                }
                sb.append("   \uD83D\uDD17 ").append(report.getUrl()).append("\n\n");
            }

            sb.append("---\n\n");
        }

        return sb.toString();
    }

    private String escapeMarkdown(String text) {
        if (text == null) {
            return "";
        }
        return text
                .replace("_", "\\_")
                .replace("*", "\\*")
                .replace("[", "\\[")
                .replace("`", "\\`");
    }
}
