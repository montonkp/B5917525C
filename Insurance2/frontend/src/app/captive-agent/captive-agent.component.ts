import { Component, OnInit } from '@angular/core';
import {CaptiveAgentService} from './captive-agent.service';
@Component({
  selector: 'app-captive-agent',
  templateUrl: './captive-agent.component.html',
  styleUrls: ['./captive-agent.component.css']
})
export class CaptiveAgentComponent implements OnInit {
  genders: Array<any>;
  genderIDSelected: number;
  provinces: Array<any>;
  provinceSelected: number;
  subDistricts: Array<any>;
  subDistrictSelected: number;
  districts: Array<any>;
  districtSelected: number;
  passwordCheck:string;
  captiveAgentObject = {
    username: null,
    password: null,
    idNumber: null,
    firstName: null,
    lastName: null,
    birthday: null,
    phone: null,
    email: null,
    address: null
  }
  constructor(private service: CaptiveAgentService) { }

  ngOnInit() {
    this.getGender();
    this.getAllProvince();    
  }
  getGender() {
    this.service.getAllgender().subscribe(res => {
      this.genders = res;
    });
  }
  getAllProvince() {
    this.service.getAllProvince().subscribe(res => {
      this.provinces = res;
    });
  }

  getAllSubDistrict() {
    this.service.getSubdtrictByDistrict(this.districtSelected).subscribe(res => {
      this.subDistricts = res;

    });
  }

  getAllDistrict() {
    this.service.getDistrictByProvince(this.provinceSelected).subscribe(res => {
      this.districts = res;
    });
  }

  postCaptiveAgentData() {
    // if(this.propertyIDSelected == null){
    //   alert('Please select property before save!');
    // }else if(this.customerObject.customerID == null){
    //   alert('Please click search before save!');
    // }else if(this.periodYear == null){
    //   alert('Please select period before save!');    
    // }else if(this.carDataSelected == null){
    //   alert('Please select car data before save!');
    // }else{
      // try {
        this.service.postCaptiveAgent(this.captiveAgentObject, this.genderIDSelected, this.subDistrictSelected, this.districtSelected,
          this.provinceSelected).subscribe(res => {
          console.log(res);
          alert('success');
        } , error1 => {
          alert(error1.error.message);
        });
      // } catch (e){
      //   if (e instanceof TypeError) {
      //     console.log(e.message);
      //     alert('Please enter date before save!');
      //   }
      // }
  //   }
  }
}
