// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: worker.proto

package edu.kit.ipd.crowdcontrol.objectservice.proto;

public final class WorkerOuterClass {
  private WorkerOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_crowdcontrol_Worker_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_crowdcontrol_Worker_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014worker.proto\022\014crowdcontrol\"5\n\006Worker\022\n" +
      "\n\002id\030\001 \001(\005\022\020\n\010platform\030\002 \001(\t\022\r\n\005email\030\003 " +
      "\001(\tB0\n,edu.kit.ipd.crowdcontrol.objectse" +
      "rvice.protoP\001b\006proto3"
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
        }, assigner);
    internal_static_crowdcontrol_Worker_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_crowdcontrol_Worker_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_crowdcontrol_Worker_descriptor,
        new java.lang.String[] { "Id", "Platform", "Email", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
