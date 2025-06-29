export interface Province{
    id?: number;
    name: string;
}
export interface Region{
    id?: number;
    name: string;
    provinceId: number;
}
export interface SubRegion {
  id?: number;
  name: string;
  regionId: number;
}

export interface School {
  id?: number;
  name: string;
  level: 'PRIMARY' | 'SECONDARY' | 'UNIVERSITY';
  subRegionId: number;
}