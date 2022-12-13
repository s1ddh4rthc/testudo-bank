const App = (props) => {
  const [count, setCount] = React.useState(1);

  return (
    <div>
      <div align="center">
        <h2>Welcome to Testudo Bank!</h2>
        <img
          src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"
          style="float: left; width: 100px; height: 100px"
        />
        <a href="/login">View Account</a> <br />
        <a href="/deposit">Deposit</a> <br />
        <a href="/withdraw">Withdraw</a> <br />
        <a href="/dispute">Dispute</a> <br />
        <a href="/transfer">Transfer</a> <br />
        <a href="/buycrypto">Buy Cryptocurrency</a>
        <a href="/sellcrypto">Sell Cryptocurrency</a> <br />
      </div>
      Counts: {count}
      <button onClick={() => setCount(count + 1)}>Click me</button>
    </div>
  );
};
