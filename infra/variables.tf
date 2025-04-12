
variable "alb_listener_arn" {}
variable "alb_sg_id" {}
variable "alb_dns" {}
variable "subnet_ids" {
  type = list(string)
}
variable "vpc_id" {}
variable "image_url" {}
variable "region" {}
variable "spring_profile" {}
variable "sqs_s3_object_created_name" {}
variable "s3_bucket_name" {}
variable "video_management_url" {}
variable "labsrole_arn" {}
variable "video_cluster_name" {}
