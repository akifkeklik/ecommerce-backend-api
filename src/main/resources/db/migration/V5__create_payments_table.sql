-- Payments table
CREATE TABLE payments (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE REFERENCES orders(id),
    amount DECIMAL(12, 2) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD',
    method VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    transaction_id VARCHAR(100) UNIQUE,
    provider VARCHAR(50),
    provider_transaction_id VARCHAR(100),
    payment_intent_id VARCHAR(100),
    card_last_four VARCHAR(4),
    card_brand VARCHAR(20),
    payer_email VARCHAR(100),
    payer_id VARCHAR(100),
    processed_at TIMESTAMP,
    failed_at TIMESTAMP,
    failure_reason VARCHAR(500),
    failure_code VARCHAR(50),
    refunded_at TIMESTAMP,
    refund_amount DECIMAL(12, 2) DEFAULT 0,
    refund_reason VARCHAR(500),
    metadata TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    version BIGINT DEFAULT 0
);
-- Indexes
CREATE INDEX idx_payment_order ON payments(order_id);
CREATE INDEX idx_payment_transaction ON payments(transaction_id);
CREATE INDEX idx_payment_status ON payments(status);
CREATE INDEX idx_payment_intent ON payments(payment_intent_id);