import { Component, OnInit, Input } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {

  searchPlace: string = "";

  constructor(private http : HttpClient){
  }

  ngOnInit(): void {
  }

  search() {
    console.log(this.searchPlace);
    this.http.get(`http://34.72.241.183:90/map/landmark?q=${this.searchPlace}`).subscribe(res => {console.log(res)});
  }
}
