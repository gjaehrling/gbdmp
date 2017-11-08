<?xml version="1.0" encoding="UTF-8"?>

<workflow-app xmlns="uri:oozie:workflow:0.5" name="OozieExample.workflow">
  <start to="decision-1"/>
  <decision name="decision-1">
    <switch>
      <case to="end"></case>
    </switch>
  </decision>
  <end name="end"/>
</workflow-app>
<!--
<workflow>
  <node name="start" x="35" y="61"/>
  <node name="end" x="500" y="100"/>
  <node name="decision-1" x="186" y="125"/>
</workflow>-->
