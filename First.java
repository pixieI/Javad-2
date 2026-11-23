@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Seat other)) return false;
            return this.row == other.row && this.number == other.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }