package sapient.coding.interview;

public enum TransactionType {
	BUY{

		@Override
		public boolean isOppositeTrade(TransactionType t) {
			return t.toString().equalsIgnoreCase("SELL");
		}
		
	}, 
	SELL{

		@Override
		public boolean isOppositeTrade(TransactionType t) {
			return t.toString().equalsIgnoreCase("BUY");
		}
		
	}, 
	DEPOSIT{

		@Override
		public boolean isOppositeTrade(TransactionType t) {
			return t.toString().equalsIgnoreCase("WITHDRAW");
		}
		
	}, 
	WITHDRAW{

		@Override
		public boolean isOppositeTrade(TransactionType t) {
			return t.toString().equalsIgnoreCase("DEPOSIT");
		}
		
	};
	
	public abstract boolean isOppositeTrade(TransactionType t);
}
