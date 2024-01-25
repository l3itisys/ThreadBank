import requests
import threading

SERVER_URL = "http://localhost:5000/submit"
NUM_THREADS = 1000
POST_DATA_TEMPLATE = {
    'account': '123',
    'value': '1',
    'toAccount': '456'
}

def send_request():
    try:
        response = requests.post(SERVER_URL, data=POST_DATA_TEMPLATE)
        if response.status_code != 200:
            print(f"Error: {response.status_code}, {response.text}")
    except Exception as e:
        print(f"Request failed: {e}")

def check_account_balance(account_id, expected_balance):
    balance_url = f"http://localhost:5000/balance/{account_id}"
    try:
        response = requests.get(balance_url)
        if response.status_code == 200:
            actual_balance = int(response.text)
            assert actual_balance == expected_balance, f"Balance mismatch for account {account_id}"
            print(f"Account {account_id} balance verified: {actual_balance}")
        else:
            print(f"Error fetching balance for account {account_id}: {response.status_code}")
    except Exception as e:
        print(f"Error checking balance for account {account_id}: {e}")

def main():
    threads = [threading.Thread(target=send_request) for _ in range(NUM_THREADS)]

    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    # Assuming initial balances are known and stored in these variables
    initial_balance_account_123 = 2000
    initial_balance_account_456 = 2000
    transfer_amount = 1 * NUM_THREADS

    # Check final balances
    check_account_balance(123, initial_balance_account_123 - transfer_amount)
    check_account_balance(456, initial_balance_account_456 + transfer_amount)

if __name__ == "__main__":
    main()

