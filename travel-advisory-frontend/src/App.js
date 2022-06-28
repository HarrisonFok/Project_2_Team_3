import React, { useState, useEffect } from "react";

const App = () => {
  const [isStructured, setIsStructured] = useState(false);
  const [isStateInfo, setIsStateInfo] = useState(false);
  const [isLandmark, setIsLandmark] = useState(false);
  const [results, setResults] = useState([]);
  const [searchItem, setSearchItem] = useState("");

  // useEffect(() => {
  //   const fetchResults = async () => {
  //     // let results = await fetch("http://localhost:7000/map/state-info?state=Texas&format=json&countrycodes=us")
  //     if (searchItem !== "") {
  //       let results = await fetch(`http://localhost:7000/map/landmark?q=${searchItem}`)
  //       setResults(await results.json())
  //     }
  //   }
  //   fetchResults();
  //   console.log(results)
  // }, [results]);

  const submitHandler = async (e) => {
    e.preventDefault();
    let fetchStr;
    if (isStructured) {
      fetchStr = "";
    } else if (isStateInfo) {
      fetchStr = `http://localhost:7000/map/state-info?state=${searchItem}&format=json&countrycodes=us`
    } else if (isLandmark) {
      fetchStr = `http://localhost:7000/map/landmark?q=${searchItem}`
    }
    let results = await fetch(fetchStr)
    setResults(await results.json())
    console.log(results)
    // let map = await fetch(`http://localhost:7000/map/${searchItem}`)
    // console.log(map)
  }

  const changeFilter = (filter) => {
    switch(filter) {
      case "structured":
        setIsStructured(true);
        setIsStateInfo(false);
        setIsLandmark(false);
        break;
      case "state-info":
        setIsStructured(false);
        setIsStateInfo(true);
        setIsLandmark(false);
        break;
      case "landmark":
        setIsStructured(false);
        setIsStateInfo(false);
        setIsLandmark(true);
        break;
    }
  }

  return (
    <div className="App">
      <button onClick={(e) => changeFilter("structured")}>By Structured</button>
      <button onClick={(e) => changeFilter("state-info")}>By State Info</button>
      <button onClick={(e) => changeFilter("landmark")}>By Landmark</button>

      <form onSubmit={submitHandler}>
        <input value={searchItem} onChange={(e) => setSearchItem(e.target.value)} onSubmit={submitHandler}/>
        <button>Search</button>
      </form>
      
      {results ? results.map(res => <p key={Math.random() * 10000}>{res.display_name}</p>) : ""}
    </div>
  );
}

export default App;
