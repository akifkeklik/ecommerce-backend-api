-- Shipments table
CREATE TABLE shipments (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE REFERENCES orders(id),
    method VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    carrier VARCHAR(50),
    tracking_number VARCHAR(100),
    tracking_url VARCHAR(500),
    -- Shipping Address
    ship_to_street VARCHAR(255),
    ship_to_address_line2 VARCHAR(255),
    ship_to_city VARCHAR(100),
    ship_to_state VARCHAR(100),
    ship_to_postal_code VARCHAR(20),
    ship_to_country VARCHAR(100),
    ship_to_phone VARCHAR(20),
    shipping_cost DECIMAL(12, 2),
    weight DOUBLE PRECISION,
    estimated_delivery TIMESTAMP,
    shipped_at TIMESTAMP,
    delivered_at TIMESTAMP,
    delivery_instructions TEXT,
    signature_required BOOLEAN DEFAULT FALSE,
    signed_by VARCHAR(100),
    notes TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    version BIGINT DEFAULT 0
);
-- Indexes
CREATE INDEX idx_shipment_order ON shipments(order_id);
CREATE INDEX idx_shipment_tracking ON shipments(tracking_number);
CREATE INDEX idx_shipment_status ON shipments(status);