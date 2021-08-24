import { Component, OnInit } from '@angular/core';
import { People } from '../people';
import { PeopleService } from '../people.service';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  peoples: People[] | undefined;
  constructor(private peopleService: PeopleService) { }

  ngOnInit(): void {
    this.peopleService.getPeople().subscribe((data: People[]) => {
      console.log(data);
      this.peoples = data;
  });
  }
}
