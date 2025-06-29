import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Province,Region,SubRegion,School } from '../models/geography.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ConfigureSchoolsService {
  list() {
    throw new Error('Method not implemented.');
  }
  private api = "http://localhost:3030/api/geography";

  constructor(private http: HttpClient) {}
  //Provinces
  getProvinces():Observable<Province[]>{
      return this.http.get<Province[]>(`${this.api}/provinces`);
    }
  addProvince(data:Province):Observable<Province>{
    return this.http.post<Province>(`${this.api}/provinces`, data);

  }  
  // Regions
  getRegions(): Observable<Region[]> {
    return this.http.get<Region[]>(`${this.api}/regions`);
  }

  addRegion(provinceId: number, data: Region): Observable<Region> {
    return this.http.post<Region>(`${this.api}/regions/${provinceId}`, data);
  }

  // Sub-regions
  getSubRegions(): Observable<SubRegion[]> {
    return this.http.get<SubRegion[]>(`${this.api}/sub-regions`);
  }

  addSubRegion(regionId: number, data: SubRegion): Observable<SubRegion> {
    return this.http.post<SubRegion>(`${this.api}/sub-regions/${regionId}`, data);
  }

  // Schools (you may add CRUD here)

}
