
public class CompoundInterest {
	double principal;
	double yearlyInterestRate;
	double monthlyRepayment;
	double totalInterestPaid = 0.0;
	
	private void addInterest(){
		double thisMonthsInterest = (principal*(yearlyInterestRate/12))/100;
		totalInterestPaid = totalInterestPaid + thisMonthsInterest;
		principal = principal + thisMonthsInterest;
		System.out.println("principal : " + principal + " , interest payed : " + thisMonthsInterest);
	}
	private void deductMonthlyPayment(){
		principal = principal - monthlyRepayment;
		System.out.println("principal after monthly repayment : " + principal);
	}
	
	public void go(){
		for(int i = 1 ; principal > 0.0 ; i++){
			System.out.println("\n\nMONTH #"+i);
			deductMonthlyPayment();
			// one month passes
			addInterest();
		}
		System.out.println("\n\nTotal Interest paid : "+totalInterestPaid);
	}
	
	public CompoundInterest(){
		principal = 260000;
		monthlyRepayment = 10000;
		yearlyInterestRate = 12;
		go();
	}
	
	public static void main(String[] args) {
		new CompoundInterest();
	}
}
