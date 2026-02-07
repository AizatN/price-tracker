INSERT INTO ${schema.name}.product_offers (id, source_id, product_id, url, parse_type, parser_config, last_parse_attempt, last_success_parse, updated_at)
VALUES
(1, 1, 1, 'https://tatphone.ru/apple-iphone-17-pro-512gb-silver-esim', 'HTML',
'{
  "title": "h1.product_title:matchesOwn((?i)iphone\\s*17\\s*pro)",
  "price": "div.wd-single-price span.woocommerce-Price-amount bdi"
}'::jsonb, null, null, now()),
(2, 1, 2, 'https://tatphone.ru/apple-iphone-17-pro-512gb-silver', 'HTML',
'{
  "title": "h1.product_title:matchesOwn((?i)iphone\\s*17\\s*pro)",
  "price": "div.wd-single-price span.woocommerce-Price-amount bdi"
}'::jsonb, null, null, now()),
(3, 2, 1, 'https://apples116.ru/product/iphone-17-pro', 'JS',
'{
  "title": "iPhone 17 Pro (512Gb / Silver / ESIM only)",
  "price": "div.wd-single-price span.woocommerce-Price-amount bdi"
}'::jsonb, null, null, now()),
(4, 2, 2, 'https://apples116.ru/product/iphone-17-pro', 'JS',
'{
  "title": "iPhone 17 Pro (512Gb / Silver / SIM+ESIM)",
  "price": "div.wd-single-price span.woocommerce-Price-amount bdi"
}'::jsonb, null, null, now())
ON CONFLICT (id) DO UPDATE SET
    source_id = EXCLUDED.source_id,
    product_id = EXCLUDED.product_id,
    url = EXCLUDED.url,
    parse_type = EXCLUDED.parse_type,
    parser_config = EXCLUDED.parser_config,
    updated_at = EXCLUDED.updated_at;
