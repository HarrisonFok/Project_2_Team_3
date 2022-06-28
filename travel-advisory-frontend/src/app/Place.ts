export interface Place {
  place_id?: string;
  osm_id?: string;
  osm_type?: string;
  licence?: string;
  lat?: string;
  lon?: string;
  boundingbox?: string[];
  class?: string;
  type?: string;
  display_name?: string;
  display_place?: string;
  display_address?: string;
  address?: {}
}
