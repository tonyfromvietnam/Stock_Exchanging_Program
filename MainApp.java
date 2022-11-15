/**
* ECS414U - Object Oriented Programming
* Queen Mary University of London, 2021/22.
* Investment trading app. Author: Duy Chinh Dinh.
*/

import java.awt.*;
import java.awt.event.*;

public class MainApp extends Frame {
    
    // This method is used to print message to user.
    //
    private static TextArea infoArea = new TextArea("Investment Trading App. Made by Duy Chinh Dinh.\nYour balance: 0£");
	
    public static void print(String text) {
		infoArea.setText(text);
    }    
    // End of method.

	private User user;
    private Agent agent;
    private Panel clientButtonsPanel;


	// This method prints the list of the assets.
	//
    public void printAssetList() {
		String text = "Market" + "\n---------------\n\n";
		int i = 1;
		for (Asset asset : agent.getAssets()) {
			text += (i) + ". "+ asset.getAssetInfo() + "\n";
			i += 1;
		}
		print(text);
    } // End of method.
    

    // This methods add a type of assets into the market.
    //
    public void addStock(String name, String symbol, int price, int available) {
		Stock stock = new Stock(name, symbol, price, available);
		agent.addStock(stock);
    }

	public void addBond(String name, int price, int available, int interest) {
		Bond bond = new Bond(name, price, available, interest);
		agent.addBond(bond);
    }
    
	public void addRealEstate(String name, int price, int area) {
		RealEstate realEstate = new RealEstate(name, price, area);
		agent.addRealEstate(realEstate);
    }
	// End of methods.


	
	// This method tries to convert intergers and handle exception. 
	//
	public int TryParseInt(String someText) {
		try {
		   return Integer.parseInt(someText);
		} catch (NumberFormatException e) {
		   return -1;
		}
	} // End of method.
	

    public MainApp() {

	this.user = new User();
	this.agent = new Agent();	
	this.setLayout(new FlowLayout());
	
	// This button is used to view the user's portfolio.
	//
	Button reportButton = new Button("View Portfolio");
	reportButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			print(user.portfolioInfo());
		}
	});
	this.add(reportButton);

	// This button is used for deposit function.
	//
	Button depositButton = new Button("Deposit");
	depositButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {		
		Prompt acp = new Prompt();

		TextArea amount = new TextArea();
		
		acp.addSubmitListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int convertInt = TryParseInt(amount.getText());
				if (convertInt >= 0) {
					user.deposit(Integer.parseInt(amount.getText()));
					print(user.portfolioInfo());
				} else {
					print("The transcation is NOT successfull. Please try again.");				
				}
			}
		});

		acp.add(amount);			
		acp.activate();
	}
	});
	this.add(depositButton); 


	// This button is used for withdrawl function.
	//
	Button withdrawButton = new Button("Withdraw");
	withdrawButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {		
		print(user.portfolioInfo());
		Prompt acp = new Prompt();

		TextArea amount = new TextArea();
		
		acp.addSubmitListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int convertInt = TryParseInt(amount.getText());
				if (convertInt >= 0) {
					if (user.getBalance() >= convertInt) {
						user.withdraw(Integer.parseInt(amount.getText()));
						print(user.portfolioInfo());
					} else {
						print("The desired amount is more than you account. Please try again.");
					}
				} else {
					print("The transcation is NOT successfull. Please try again.");
				}
			}	
		});

		acp.add(amount);			
		acp.activate();
	}
	});
	this.add(withdrawButton); 
	

	// This button is used for market viewing function.
	//
	Button marketButton = new Button("View Market");
	marketButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			printAssetList();
		}
	});
	this.add(marketButton); 


	// This button is used to buy shares.
	//
	Button buyStocks = new Button("Buy Shares (Sotcks/Bonds)");
	buyStocks.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		print("---------------\n*Fill the company name or the symbol on the left box\nThe right box is for the amount you want to buy.\n\n---------------\n" + user.portfolioInfo());
		Prompt buy = new Prompt();

		TextArea amount = new TextArea();
		TextArea name = new TextArea();
		
		buy.addSubmitListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int convertInt = TryParseInt(amount.getText());
				if (convertInt >= 0) {
					boolean result = agent.buyShares(user, name.getText(), Integer.parseInt(amount.getText()));
					if (result == true) {
						print(user.portfolioInfo());
					} else {
						print("The buying process is NOT successfull. Please try again.");
					} 
				} else {
				print("The transcation is NOT successfull. Please try again.");
				}
			}
		});

		buy.add(name);
		buy.add(amount);
		buy.activate();
	}
	});
	this.add(buyStocks); 


	// This button is used to sell shares.
	//
	Button sellStocks = new Button("Sell Shares (Stocks/Bonds)");
	sellStocks.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		print("---------------\n*Fill the company name or the symbol on the left box, the right box is for the amount you want to sell.\n\n---------------\n" + user.portfolioInfo());
		Prompt buy = new Prompt();

		TextArea amount = new TextArea();
		TextArea name = new TextArea();
		
		buy.addSubmitListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			int convertInt = TryParseInt(amount.getText());
			if (convertInt >= 0) {
				boolean result = agent.sellShares(user, name.getText(), Integer.parseInt(amount.getText()));
				if (result == true) {
					print(user.portfolioInfo());
				} else {
					print("The selling process is NOT successfull. Please try again.");
				} 
			} else {
				print("The transcation is NOT successfull. Please try again.");
			}
		}
		});

		buy.add(name);
		buy.add(amount);
		buy.activate();
	}
	});
	this.add(sellStocks); 


	// This button is used to buy real estate.
	//
	Button buyRealEstate = new Button("Buy Real Estate");
	buyRealEstate.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		print("---------------\n*Fill in the place you want to buy.\n\n---------------\n" + user.portfolioInfo());
		Prompt buy = new Prompt();

		TextArea place = new TextArea();
		
		buy.addSubmitListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean result = agent.buyRealEstate(user, place.getText());
				if (result == true) {
					print(user.portfolioInfo());
				} else {
					print("The buying process is NOT successfull. Please try again."); 
				}
			}
		});

		buy.add(place);
		buy.activate();
	}
	});
	this.add(buyRealEstate); 


	// This button is used to sell real estate.
	//
	Button sellRealEstate = new Button("Sell Real Estate");
	sellRealEstate.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		print("---------------\n*Fill in the place you want to sell.\n\n---------------\n" + user.portfolioInfo());
		Prompt buy = new Prompt();

		TextArea place = new TextArea();
		
		buy.addSubmitListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean result = agent.sellRealEstate(user, place.getText());
				if (result == true) {
					print(user.portfolioInfo());
				} else {
					print("The selling process is NOT successfull. Please try again."); 
				}
			}
		});

		buy.add(place);
		buy.activate();
	}
	});
	this.add(sellRealEstate); 


	// Output console
	infoArea.setEditable(false);
	this.add(infoArea);	


	// Client button panel
	clientButtonsPanel = new Panel();
	clientButtonsPanel.setLayout(new GridLayout(0,1));
	clientButtonsPanel.setVisible(true);
	this.add(clientButtonsPanel);

	
	// Add a couple of stocks.
	this.addStock("Apple Inc", "AAPL", 168, 1000);
	this.addStock("Microsoft Corp", "MSFT", 282, 2000);
	this.addStock("Amazon.com Inc", "AMZN", 3015, 2100);
	this.addStock("Tesla, Inc", "TSLA", 986, 8770);
	this.addStock("Alphabet Inc A", "GOOGL", 2554, 1231);
	this.addStock("Nvidia Corp", "NVDA", 215, 986);
	this.addStock("Alphabet Inc C", "GOOG", 2567, 7562);
	this.addStock("Berkshire Hathaway B", "BRK.B", 524271, 651);
	this.addStock("Meta Platforms, Inc. Class A", "FB", 214, 543);
	this.addStock("Unitedhealth Group Inc", "UNH", 533, 8761);

	// Add a couple of bonds.
	this.addBond("Bonnier Fastigheter", 106, 1000, 10);
	this.addBond("Loomis AB", 31, 300, 8);
	this.addBond("Republic of Philippines", 559, 70100, 7);
	this.addBond("NBN Co", 800, 599, 3);
	this.addBond("Fortescue Metals Group", 800, 800, 12);
	this.addBond("Johnson Matthey", 344, 315, 11);
	this.addBond("VEF", 53, 500, 4);
	this.addBond("Council of Europe Development Bank", 1094, 1000, 12);
	this.addBond("Vonovia", 750, 80, 8);
	this.addBond("CapMan Plc", 40, 43, 8);

	// Add a couple of real estates.
	this.addRealEstate("London", 63151, 6211);
	this.addRealEstate("Manchester", 1652, 1221);
	this.addRealEstate("Birmingham", 435, 987);
	this.addRealEstate("Brighton", 6589, 643);
	this.addRealEstate("Paris", 8780, 879);
	this.addRealEstate("New York", 534, 123);
	this.addRealEstate("Hanoi", 652, 89797);
	this.addRealEstate("Ho chi minh city", 9872, 31231);
	this.addRealEstate("Ninh Binh", 8768, 88791);
	this.addRealEstate("Da Nang", 123, 321);

	// This is just so the X button closes the app
	//
	WindowCloser wc = new WindowCloser();
	this.addWindowListener(wc); 

	this.setSize(500,500); // Set the size of the program.
	this.setLocationRelativeTo(null); // Centers the window on the screen.
	this.setVisible(true);

    }
    
    public static void main(String[] args) {
		new MainApp();
    }

}
