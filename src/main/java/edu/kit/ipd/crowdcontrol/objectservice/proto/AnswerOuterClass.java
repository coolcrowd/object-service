// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: answer.proto

package edu.kit.ipd.crowdcontrol.objectservice.proto;

public final class AnswerOuterClass {
  private AnswerOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_crowdcontrol_Answer_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_crowdcontrol_Answer_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014answer.proto\022\014crowdcontrol\032\014rating.pro" +
      "to\"\322\001\n\006Answer\022\023\n\013reservation\030\001 \001(\005\022\n\n\002id" +
      "\030\002 \001(\005\022\025\n\rexperiment_id\030\003 \001(\005\022\016\n\006worker\030" +
      "\004 \001(\005\022\017\n\007content\030\005 \001(\t\022\014\n\004time\030\006 \001(\003\022\017\n\007" +
      "quality\030\007 \001(\005\022\026\n\016systemresponse\030\010 \001(\t\022%\n" +
      "\007ratings\030\t \003(\0132\024.crowdcontrol.Rating\022\021\n\t" +
      "duplicate\030\n \001(\010B0\n,edu.kit.ipd.crowdcont" +
      "rol.objectservice.protoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          edu.kit.ipd.crowdcontrol.objectservice.proto.RatingOuterClass.getDescriptor(),
        }, assigner);
    internal_static_crowdcontrol_Answer_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_crowdcontrol_Answer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_crowdcontrol_Answer_descriptor,
        new java.lang.String[] { "Reservation", "Id", "ExperimentId", "Worker", "Content", "Time", "Quality", "Systemresponse", "Ratings", "Duplicate", });
    edu.kit.ipd.crowdcontrol.objectservice.proto.RatingOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
