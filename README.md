# TestNaukri

Problem Statement
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
T1	1	CP-1	B1	20/05/2020	<today date>	N
T2	2	CP-2	B1	20/05/2021	<today date>	N
T2	1	CP-1	B1	20/05/2021	14/03/2015	N
T3	3	CP-3	B2	20/05/2014	<today date>	Y

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.

# Solution
1.  Store the data in h2 (in memory database).
 Created Following rest end points
 1. One for Saving. Following criteria used for saving
     A. For saving trade record in Trade DB
        Following Validation added while saving record
        1. If MaturityDate < today date then throw an Error Inccorect Maturity Date
        2. If Version < Existing version then throw an Error Incorrect Version
     B. If Trade is exist then check version no
         1. If version matches then update same reccord
         2. If Version  > existing version then add new record
     C.  If TradeId does not exist then add new record
 2    To Fecth all records
 
 3. Schedular is written to update expiry to Y if maturityDt is already crossed
   
